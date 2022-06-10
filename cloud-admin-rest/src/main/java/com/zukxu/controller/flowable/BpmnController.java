package com.zukxu.controller.flowable;

import com.zukxu.common.core.response.R;
import com.zukxu.common.core.response.RResponse;
import com.zukxu.controller.BaseController;
import com.zukxu.flowable.model.vo.ActivityVo;
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
    public R<String> stopBpmn(@PathVariable String modelId) {
        return flowableBpmnService.stopBpmn(modelId);
    }

    /**
     * 通过模型id获取模型信息
     *
     * @param modelId 模型id
     *
     * @return
     */
    @GetMapping("/getBpmnByModelId/{modelId}")
    public ModelInfoVo getBpmnByModelId(@PathVariable String modelId) {
        return flowableBpmnService.loadBpmnXmlByModelId(modelId);
    }

    /**
     * 通过模型Key获取模型信息
     *
     * @param modelKey 模型key
     *
     * @return
     */
    @GetMapping("/getBpmnByModelKey/{modelKey}")
    public ModelInfoVo getBpmnByModelKey(@PathVariable String modelKey) {
        return flowableBpmnService.loadBpmnXmlByModelKey(modelKey);
    }

    /**
     * 通过流程实例id获取流程图和高亮线和高亮节点
     *
     * @param processInstanceId 流程实例id
     *
     * @return
     */
    @GetMapping("/getHighLightInfoByInstanceId/{processInstanceId}")
    public HighLightedNodeVo getHighLightInfoByInstanceId(@PathVariable String processInstanceId) {
        return flowProcessDiagramService.getHighLightInfoByInstanceId(processInstanceId);
    }

    /**
     * 根据流程实例id获取节点的信息
     *
     * @param processInstanceId 流程实例id
     * @param activityId        节点id
     *
     * @return
     */
    @GetMapping("/getActivityByProcessInstanceIdAndActivityId/{processInstanceId}/{activityId}")
    public ActivityVo getActivityByProcessInstanceIdAndActivityId(@PathVariable String processInstanceId, @PathVariable String activityId) {
        return flowProcessDiagramService.getActivityByProcessInstanceIdAndActivityId(processInstanceId, activityId);
    }

    /**
     * 通过流程实例id获取每个节点的审批人信息
     *
     * @param processInstanceId 流程实例id
     *
     * @return
     */
    @GetMapping("/listActivitiesByProcessInstanceId/{processInstanceId}")
    public List<ActivityVo> listActivitiesByProcessInstanceId(@PathVariable String processInstanceId) {
        return flowProcessDiagramService.listActivitiesByProcessInstanceId(processInstanceId);
    }

}
