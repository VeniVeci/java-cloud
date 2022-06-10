package com.zukxu.flowable.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zukxu.common.core.exception.BusinessException;
import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RStatus;
import com.zukxu.common.security.service.LoginUser;
import com.zukxu.flowable.json.convert.converter.CustomBpmnJsonConverter;
import com.zukxu.flowable.mapper.FlowableProcessDefinitionMapper;
import com.zukxu.flowable.model.FlowModelInfo;
import com.zukxu.flowable.model.FlowableProcessDefinition;
import com.zukxu.flowable.model.enums.FlowStatusEnums;
import com.zukxu.flowable.model.vo.ModelInfoVo;
import com.zukxu.flowable.handler.FlowableFactory;
import com.zukxu.flowable.service.IFlowModelInfoService;
import com.zukxu.flowable.service.IFlowableBpmnService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.language.json.converter.BaseBpmnJsonConverter;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.engine.repository.Deployment;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.common.util.XmlUtil;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.service.ConverterContext;
import org.flowable.validation.ProcessValidator;
import org.flowable.validation.ProcessValidatorFactory;
import org.flowable.validation.ValidationError;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 10:54:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlowableBpmnServiceImpl extends FlowableFactory implements IFlowableBpmnService {

    //@formatter:off
    //flowable
    final private ProcessValidatorFactory processValidatorFactory;
    final protected BpmnXMLConverter bpmnXMLConverter;
    //custom util
    final protected ObjectMapper objectMapper;
    final protected CustomBpmnJsonConverter bpmnJsonConverter;
    //custom service
    final protected IFlowModelInfoService flowModelInfoService;
    //custom mapper
    final private FlowableProcessDefinitionMapper flowableProcessDefinitionMapper;
    //@formatter:on
    @Override
    @SneakyThrows
    public R<String> importBpmnModel(String modelId, String fileName, InputStream modelStream, LoginUser user) {
        String msg = "";
        Model processModel = modelService.getModel(modelId);
        if(StrUtil.isBlank(fileName)) {
            fileName = processModel.getKey() + BPMN_EXTENSION;
        }
        if(StrUtil.endWithAny(fileName, XML_EXTENSION, BPMN_EXTENSION, BPMN20_XML_EXTENSION)) {
            try {
                XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
                InputStreamReader xmlIn = new InputStreamReader(modelStream, StandardCharsets.UTF_8);
                XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
                BpmnModel bpmnModel = bpmnXMLConverter.convertToBpmnModel(xtr);
                bpmnModel.getMainProcess().setId(processModel.getKey());
                bpmnModel.setTargetNamespace(BaseBpmnJsonConverter.NAMESPACE);
                if(CollectionUtil.isEmpty(bpmnModel.getProcesses())) {
                    msg = "No process found in definition ";
                    log.error("{}{}", msg, fileName);
                    return R.fail(msg + fileName);
                }
                if(bpmnModel.getLocationMap().size() == 0) {
                    msg = "No required BPMN DI information found in definition ";
                    log.error("{}{}", msg, fileName);
                    return R.fail(msg + fileName);
                }
                ConverterContext converterContext = new ConverterContext(modelService, objectMapper);
                List<AbstractModel> decisionTables = modelService.getModelsByModelType(AbstractModel.MODEL_TYPE_DECISION_TABLE);
                decisionTables.forEach(abstractModel -> {
                    Model model = (Model) abstractModel;
                    converterContext.addDecisionTableModel(model);
                });
                ObjectNode modelNode = bpmnJsonConverter.convertToJson(bpmnModel, converterContext);
                this.setProcessPropertiesToKey(modelNode, processModel.getKey());
                AbstractModel savedModel = modelService.saveModel(modelId, processModel.getName(), processModel.getKey(),
                                                                  processModel.getDescription(), modelNode.toString(), false,
                                                                  null, user.getId());
                FlowModelInfo flowModelInfo = new FlowModelInfo().setModelId(savedModel.getId())
                                                                 .setStatus(FlowStatusEnums.DFB.getStatus())
                                                                 .setExtendStatus(FlowStatusEnums.DFB.getStatus());
                flowModelInfoService.saveOrUpdate(flowModelInfo);
                return R.ok(savedModel.getId());
            } catch(BadRequestException e) {
                throw e;
            } catch(Exception e) {
                throw new BusinessException("bpmn.js failed for " + fileName + ", error message " + e.getMessage());
            }
        } else {
            msg = "Invalid file name, only .xml .bpmn and .bpmn20.xml files are supported not ";
            log.error("{}{}", msg, fileName);
            return R.fail(msg + fileName);
        }
    }

    @Override
    public R<String> publishBpmn(String modelId) {
        Model model = modelService.getModel(modelId);
        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        R<String> r = this.validationErrors(bpmnModel);
        if(!r.isSuccess()) {
            return r;
        }
        FlowModelInfo modelInfo = flowModelInfoService.getOne(new LambdaQueryWrapper<FlowModelInfo>().eq(FlowModelInfo::getModelId, modelId));
        if(ObjectUtil.isNotNull(modelInfo)) {
            //校验是否可用
            r = FlowStatusEnums.checkActive(modelInfo.getStatus(), modelInfo.getExtendStatus());
            if(r.isSuccess()) {
                //部署
                this.deployBpmn(modelInfo);
                LambdaUpdateWrapper<FlowModelInfo> modelInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                modelInfoLambdaUpdateWrapper.set(FlowModelInfo::getStatus, FlowStatusEnums.YFB.getStatus())
                                            .set(FlowModelInfo::getExtendStatus, FlowStatusEnums.YFB.getStatus())
                                            .eq(FlowModelInfo::getModelId, modelId);
                flowModelInfoService.update(modelInfoLambdaUpdateWrapper);
            } else {
                return r;
            }
        } else {
            return r.setCode(RStatus.FAIL.getCode()).setMsg("没有找到对应的模型，请确认!");
        }
        return r;
    }

    @Override
    public R<Deployment> deployBpmn(FlowModelInfo modelInfo) {
        Model model = modelService.getModel(modelInfo.getModelId());
        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        Deployment deploy = repositoryService.createDeployment()
                                             .name(model.getName())
                                             .key(model.getKey())
                                             .category(modelInfo.getCategoryCode())
                                             .tenantId(model.getTenantId())
                                             .addBpmnModel(model.getKey() + BPMN_EXTENSION, bpmnModel)
                                             .deploy();
        flowableProcessDefinitionMapper.update(new FlowableProcessDefinition().setCategory(modelInfo.getCategoryCode()),
                                               new LambdaQueryWrapper<FlowableProcessDefinition>()
                                                       .eq(FlowableProcessDefinition::getDeploymentId, deploy.getId()));
        return R.ok(deploy);
    }

    @Override
    public R<String> stopBpmn(String modelId) {
        LambdaUpdateWrapper<FlowModelInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(FlowModelInfo::getModelId, modelId).set(FlowModelInfo::getStatus, FlowStatusEnums.TY.getStatus())
               .set(FlowModelInfo::getExtendStatus, FlowStatusEnums.TY.getStatus());
        boolean update = flowModelInfoService.update(wrapper);
        return update ? R.ok() : R.fail("终止异常");
    }

    @Override
    public ModelInfoVo loadBpmnXmlByModelId(String modelId) {
        Model model = modelService.getModel(modelId);
        byte[] bpmnXML = modelService.getBpmnXML(model);
        String streamStr = new String(bpmnXML);
        return new ModelInfoVo()
                .setModelId(modelId)
                .setModelName(model.getName())
                .setModelKey(model.getKey())
                .setFileName(model.getName())
                .setModelXml(streamStr);
    }

    @Override
    public ModelInfoVo loadBpmnXmlByModelKey(String modelKey) {
        if(StrUtil.isNotBlank(modelKey)) {
            Optional.ofNullable(flowModelInfoService.getModelInfoByModelKey(modelKey)).ifPresent(t -> this.loadBpmnXmlByModelId(t.getModelId()));
        }
        return null;
    }

    /** =============================================================== */
    private void setProcessPropertiesToKey(ObjectNode modelNode, String key) {
        ObjectNode objectNode = (ObjectNode) modelNode.get("properties");
        objectNode.put("process_id", key);
        objectNode.put("processid", key);
    }

    /**
     * 校验model 是否存在问题
     *
     * @param bpmnModel model
     *
     * @return
     */
    private R<String> validationErrors(BpmnModel bpmnModel) {
        ProcessValidator processValidator = processValidatorFactory.createDefaultProcessValidator();
        List<ValidationError> validationErrors = processValidator.validate(bpmnModel);
        if(CollectionUtils.isNotEmpty(validationErrors)) {
            StringBuffer message = new StringBuffer();
            validationErrors.forEach(validationError -> message.append(validationError.toString()).append("\n"));
            return R.fail(message.toString());
        }
        return R.ok();
    }

}
