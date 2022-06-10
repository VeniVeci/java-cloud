package com.zukxu.flowable.service;

import com.zukxu.flowable.model.vo.ActivityVo;
import com.zukxu.flowable.model.vo.HighLightedNodeVo;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 9:46:57
 */
public interface IFlowProcessDiagramService {

    /**
     * 根据 流程id获取流程图相关信息
     *
     * @param processInstanceId pid
     *
     * @return HighLightedNodeVo
     */
    HighLightedNodeVo getHighLightInfoByInstanceId(String processInstanceId);

    /**
     * 根据流程实例id获取节点的信息
     *
     * @param processInstanceId processInstanceId
     * @param activityId        activityId
     *
     * @return ActivityVo
     */
    ActivityVo getActivityByProcessInstanceIdAndActivityId(String processInstanceId, String activityId);

    /**
     * 通过流程实例id获取每个节点的审批人信息
     *
     * @param processInstanceId processInstanceId
     *
     * @return List<ActivityVo>
     */
    List<ActivityVo> listActivitiesByProcessInstanceId(String processInstanceId);

}
