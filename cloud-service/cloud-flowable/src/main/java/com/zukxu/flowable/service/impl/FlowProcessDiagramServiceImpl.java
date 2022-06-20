package com.zukxu.flowable.service.impl;

import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.zukxu.flowable.handler.FlowableFactory;
import com.zukxu.flowable.model.ExtendHisprocinst;
import com.zukxu.flowable.model.vo.ActivityVo;
import com.zukxu.flowable.model.vo.HighLightedNodeVo;
import com.zukxu.flowable.service.IFlowProcessDiagramService;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 16:54:30
 */
@Service
public class FlowProcessDiagramServiceImpl extends FlowableFactory implements IFlowProcessDiagramService {

    @Override
    public HighLightedNodeVo getHighLightInfoByInstanceId(String processInstanceId) {
        //TODO
        //从缓存中获取
        HighLightedNodeVo highLightedNodeVo = this.findHighLightInfoByInstanceId(processInstanceId);
        //入到redis中
        return highLightedNodeVo;
    }

    @Override
    public ActivityVo getActivityByProcessInstanceIdAndActivityId(String processInstanceId, String activityId) {
        //TODO
        return null;
    }

    @Override
    @SneakyThrows
    public List<ActivityVo> listActivitiesByProcessInstanceId(String processInstanceId) {
        //TODO 从缓存中获取
        List<ActivityVo> activityVos = new ArrayList<>();
        ExtendHisprocinst extendHisprocinst = extendHisprocinstService.findExtendHisprocinstByProcessInstanceId(processInstanceId);
        if(extendHisprocinst == null) {
            throw new FlowException(String.format("通过流程实例ID【%s】未找到扩展流程实例历史数据！", processInstanceId));
        }
        BpmnModel bpmnModel = bpmnModelService.getBpmnModelByProcessDefId(extendHisprocinst.getProcessDefinitionId());
        List<UserTask> userTasks = bpmnModelService.findUserTasksByBpmnModel(bpmnModel);
        if(CollectionUtils.isNotEmpty(userTasks)) {
            List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery()
                                                                             .processInstanceId(processInstanceId)
                                                                             .list();
            Map<String, HistoricTaskInstance> historicTaskInstanceMap = new HashMap<>();
            if(CollectionUtils.isNotEmpty(historicTaskInstances)) {
                historicTaskInstanceMap = historicTaskInstances.stream()
                                                               .collect(Collectors.toMap(HistoricTaskInstance::getTaskDefinitionKey,
                                                                                         Function.identity(),
                                                                                         (key1, key2) -> key2));
            }
            for(UserTask userTask : userTasks) {
                ActivityVo vo = null;
                if(!historicTaskInstanceMap.containsKey(userTask.getId())) {
                    vo = this.setUnStartTaskNodeInfo(userTask, bpmnModel, extendHisprocinst);
                    vo.setStatus(NodeStatusEnum.PENDING.getDescription());
                } else {
                    HistoricTaskInstance historicTaskInstance = historicTaskInstanceMap.get(userTask.getId());
                    vo = this.setUserTask(historicTaskInstances, historicTaskInstance, userTask, bpmnModel, extendHisprocinst);
                }
                activityVos.add(vo);
            }
        }
        cache.put(processInstanceId, activityVos);
        return activityVos;
    }

    /** ================================================== */
    private HighLightedNodeVo findHighLightInfoByInstanceId(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        List<String> activeActivityIds = new ArrayList<>();
        List<String> highLightedFlows = new ArrayList<>();
        List<HistoricActivityInstance> historicSquenceFlows = historyService.createHistoricActivityInstanceQuery()
                                                                            .processInstanceId(processInstanceId)
                                                                            .activityType(BpmnXMLConstants.ELEMENT_SEQUENCE_FLOW)
                                                                            .list();
        historicSquenceFlows.forEach(historicActivityInstance -> highLightedFlows.add(historicActivityInstance.getActivityId()));
        String processDefinitionId = null;
        String modelName = null;
        if(processInstance == null) {
            ExtendHisprocinst extendHisprocinst = extendHisprocinstService.findExtendHisprocinstByProcessInstanceId(processInstanceId);
            processDefinitionId = extendHisprocinst.getProcessDefinitionId();
            List<HistoricActivityInstance> historicEnds = historyService.createHistoricActivityInstanceQuery()
                                                                        .processInstanceId(processInstanceId)
                                                                        .activityType(BpmnXMLConstants.ELEMENT_EVENT_END)
                                                                        .list();
            List<String> finalActiveActivityIds = activeActivityIds;
            historicEnds.forEach(historicActivityInstance -> finalActiveActivityIds.add(historicActivityInstance.getActivityId()));
            modelName = extendHisprocinst.getProcessName();
        } else {
            processDefinitionId = processInstance.getProcessDefinitionId();
            activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
            modelName = processInstance.getName();
        }
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        byte[] bpmnXML = modelService.getBpmnXML(bpmnModel);
        String modelXml = new String(bpmnXML, StandardCharsets.UTF_8);
        return new HighLightedNodeVo(highLightedFlows, activeActivityIds, modelXml, modelName);
    }

}
