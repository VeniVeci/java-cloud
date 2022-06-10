package com.zukxu.controller.flowable;

import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RResponse;
import com.zukxu.controller.BaseController;
import com.zukxu.flowable.model.vo.HighLightedNodeVo;
import com.zukxu.flowable.model.vo.ModelInfoVo;
import com.zukxu.flowable.service.IFlowProcessDiagramService;
import com.zukxu.flowable.service.IFlowableBpmnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * <p>
 * 流程引擎控制器
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 9:41
 */
@RResponse
@RestController
@RequestMapping("/flowable/bpmn")
@RequiredArgsConstructor
public class BpmnController<T> extends BaseController<T> {

    //@formatter:off
    final private IFlowableBpmnService flowableBpmnService;
    final private IFlowProcessDiagramService flowProcessDiagramService;
    //@formatter:on

    /**
     * 添加bpmnModel
     *
     * @param modelInfoVo 参数
     *
     * @return
     */
    @PostMapping("/saveBpmnModel")
    public R<String> saveBpmnModel(@RequestBody ModelInfoVo modelInfoVo) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(modelInfoVo.getModelXml().getBytes());
        return flowableBpmnService.importBpmnModel(modelInfoVo.getModelId(),
                                                   modelInfoVo.getFileName(),
                                                   byteArrayInputStream,
                                                   this.getLoginUser());
    }

    /**
     * 发布Bpmn
     *
     * @param modelId 模型id
     *
     * @return
     */
    @GetMapping("/publishBpmn/{modelId}")
    public R<String> publishBpmn(@PathVariable String modelId) {
        return flowableBpmnService.publishBpmn(modelId);
    }

    /**
     * 停用Bpmn
     *
     * @param modelId 模型id
     *
     * @return
     */
    @GetMapping("/stopBpmn/{modelId}")
    public String stopBpmn(@PathVariable String modelId) {
        String returnVo = flowableBpmnService.stopBpmn(modelId);
        return returnVo;
    }

    /**
     * 获取
     *
     * @param modelId 模型id
     *
     * @return
     */
    @GetMapping("/getBpmnByModelId/{modelId}")
    public ModelInfoVo getBpmnByModelId(@PathVariable String modelId) {
        ModelInfoVo > returnVo = new > (ReturnCode.SUCCESS, "获取数据成功！");
        ModelInfoVo modelInfoVo = flowableBpmnService.loadBpmnXmlByModelId(modelId);
        returnVo.setData(modelInfoVo);
        return returnVo;
    }

    /**
     * 获取
     *
     * @param modelKey 模型key
     *
     * @return
     */
    @GetMapping("/getBpmnByModelKey/{modelKey}")
    public ModelInfoVo>

    getBpmnByModelKey(@PathVariable String modelKey) {
        ModelInfoVo > returnVo = new > (ReturnCode.SUCCESS, "获取数据成功！");
        ModelInfoVo modelInfoVo = flowableBpmnService.loadBpmnXmlByModelKey(modelKey);
        returnVo.setData(modelInfoVo);
        return returnVo;
    }

    /**
     * 通过流程实例id获取流程图和高亮线和高亮节点
     *
     * @param processInstanceId 流程实例id
     *
     * @return
     */
    @GetMapping("/getHighLightedNodeVoByProcessInstanceId/{processInstanceId}")
    public HighLightedNodeVo>

    getHighLightedNodeVoByProcessInstanceId(@PathVariable String processInstanceId) {
        HighLightedNodeVo > returnVo = new > (ReturnCode.SUCCESS, "OK");
        HighLightedNodeVo highLightedNodeVo = flowProcessDiagramService.getHighLightedNodeVoByProcessInstanceId(processInstanceId);
        returnVo.setData(highLightedNodeVo);
        return returnVo;
    }

    /**
     * 过流程实例id获取节点的信息
     *
     * @param processInstanceId 流程实例id
     * @param activityId        节点id
     *
     * @return
     */
    @GetMapping("/getOneActivityVoByProcessInstanceIdAndActivityId/{processInstanceId}/{activityId}")
    public ActivityVo>

    getOneActivityVoByProcessInstanceIdAndActivityId(@PathVariable String processInstanceId, @PathVariable String activityId) {
        ActivityVo > returnVo = new > (ReturnCode.SUCCESS, "OK");
        ActivityVo processActivityVo = flowProcessDiagramService.getOneActivityVoByProcessInstanceIdAndActivityId(processInstanceId, activityId);
        returnVo.setData(processActivityVo);
        return returnVo;
    }

    /**
     * 通过流程实例id获取每个节点的审批人信息
     *
     * @param processInstanceId 流程实例id
     *
     * @return
     */
    @GetMapping("/getProcessActivityVosByProcessInstanceId/{processInstanceId}")
    public List getProcessActivityVosByProcessInstanceId(@PathVariable String processInstanceId) {
        List > returnVo = new > (ReturnCode.SUCCESS, "OK");
        List<ActivityVo> processActivityVos = flowProcessDiagramService.getProcessActivityVosByProcessInstanceId(processInstanceId);
        returnVo.setData(processActivityVos);
        return returnVo;
    }

}
