package com.zukxu.flowable.service.impl;

import com.zukxu.flowable.model.vo.ActivityVo;
import com.zukxu.flowable.model.vo.HighLightedNodeVo;
import com.zukxu.flowable.service.IFlowProcessDiagramService;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 16:54:30
 */
@Service
public class FlowProcessDiagramServiceImpl implements IFlowProcessDiagramService {

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
    public List<ActivityVo> listActivitiesByProcessInstanceId(String processInstanceId) {
        //TODO
        return null;
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
