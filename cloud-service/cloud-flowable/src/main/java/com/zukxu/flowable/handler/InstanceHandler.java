package com.zukxu.flowable.handler;


import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.impl.cmd.AddMultiInstanceExecutionCmd;
import org.flowable.engine.impl.cmd.DeleteMultiInstanceExecutionCmd;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 流程实例处理器
 * </p>
 *
 * @author xupu
 * @since 2022/2/28 15:46
 */
@Slf4j
@Component
public class InstanceHandler extends FlowableFactory {

    /**
     * 启动流程实例 key
     *
     * @param processDefinitionKey key
     *
     * @return ProcessInstance
     */
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    public ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String tenantId, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, variables, tenantId);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    public ProcessInstance startProcessInstanceByKeyAndTenantId(String processDefinitionKey, String businessKey,
                                                                String tenantId, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService
                .startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, variables, tenantId);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    /**
     * 启动并完成第一个任务 key
     *
     * @param processDefinitionKey key
     * @param variables            variables
     * @param actorIds             用户id
     *
     * @return ProcessInstance
     */
    public ProcessInstance startInstanceAndExecuteFirstTask(String processDefinitionKey, Map<String, Object> variables, Map<String, Object> actorIds) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        log.info("启动流程实例成功，流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        Task task = taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).active().singleResult();
        taskService.complete(task.getId(), actorIds);
        log.info("第一个流程任务已执行成功taskId:{}", task.getId());
        return pi;
    }

 /*   public Map<String, Object> startInstanceAndExecuteFirstTask(String processDefinitionKey, String businessKey, String businessName, String tenantId, String userId, Map<String, Object> variables) {
        ProcessInstance pi = null;
        //设置流程发起人
        Authentication.setAuthenticatedUserId(userId);
        if(StrUtil.isNotBlank(tenantId)) {
            pi = runtimeService.startProcessInstanceByKeyAndTenantId(processDefinitionKey, businessKey, variables, tenantId);
            if(StrUtil.isNotBlank(businessName)) {
                runtimeService.setProcessInstanceName(pi.getProcessInstanceId(), businessName);
            }
        } else {
            pi = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
            if(StrUtil.isNotBlank(businessName)) {
                runtimeService.setProcessInstanceName(pi.getProcessInstanceId(), businessName);
            }
        }
        //清除流程发起人
        Authentication.setAuthenticatedUserId(null);
        assert pi != null;
        String instanceId = pi.getProcessInstanceId();
        log.info("流程实例ID:{}---流程定义ID:{}", instanceId, pi.getProcessDefinitionId());
        //查询第一个节点任务
        Task task = taskQueryHandler.queryTaskByInsId(instanceId);
        String id = task.getId();
        taskHandler.setAssignee(id, userId);
        taskHandler.setOwner(id, userId);
        task.setAssignee(userId);
        task.setOwner(userId);
        taskHandler.addComment(task.getId(),
                               task.getProcessInstanceId(),
                               FlowCommentEnum.NORMAL.getType(),
                               SpringUtil.getBean(SysUserService.class).selectUserById(userId).getUserName() + "发起流程申请");
        //查询申请人节点是否绑定表单
        //将节点表单作为启动参数进行启动任务
        if(StrUtil.isNotBlank(task.getFormKey())) {
            WorkFlowForm workFlowForm = flowFormService.selectFlowFormByKey(task.getFormKey());
            variables = FlowUtils.initFlowVariables(workFlowForm, variables);
        }
        taskService.complete(id, variables);

        //查询下一节点任务
        Task activeTask = taskQueryHandler.queryTaskByInsId(instanceId);
        Map<String, Object> map = new HashMap<>(16);
        log.info("旧任务ID{}--新任务ID:{}", id, activeTask.getId());
        //剔除返回懒加载属性，否则json解析报错
        TaskVO taskVO = BeanUtil.copyProperties(task, TaskVO.class);
        TaskVO activeTaskVO = BeanUtil.copyProperties(activeTask, TaskVO.class);

        map.put("finish", taskVO);
        map.put("active", activeTaskVO);
        map.put("pid", pi.getProcessInstanceId());
        return map;
    }
*/

    /**
     * 启动流程实例 id
     *
     * @param processDefinitionId id
     * @param variables           variables
     *
     * @return ProcessInstance
     */
    public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables) {
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinitionId, variables);
        log.info("流程实例ID:{}---流程定义ID:{}", pi.getId(), pi.getProcessDefinitionId());
        return pi;
    }

    /**
     * 中断流程实例 id
     *
     * @param processInstanceId id
     */
    public void suspendProcessInstanceById(String processInstanceId) {
        runtimeService.suspendProcessInstanceById(processInstanceId);
        log.info("成功中断流程实例ID:{}", processInstanceId);
    }

    /**
     * 激活流程实例 id
     *
     * @param processInstanceId id
     */
    public void activateProcessInstanceById(String processInstanceId) {
        runtimeService.activateProcessInstanceById(processInstanceId);
        log.info("成功激活流程实例ID:{}", processInstanceId);
    }

    /**
     * 是否挂起
     *
     * @param processInstanceId id
     *
     * @return boolean
     */
    public boolean isSuspended(String processInstanceId) {
        boolean flag = true;
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(processInstance != null) {
            flag = processInstance.isSuspended();
        }
        return flag;
    }

    /**
     * 删除流程实例 id
     *
     * @param processInstanceId id
     * @param deleteReason      删除原因
     */
    public void deleteProcessInstance(String processInstanceId, String deleteReason) {
        runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
        log.info("成功删除流程实例ID:{},{}", processInstanceId, deleteReason);
    }

    public void deleteHisProcessInstance(String processInstanceId) {
        historyService.deleteHistoricProcessInstance(processInstanceId);
        log.info("成功删除历史流程实例ID:{}", processInstanceId);
    }

    /**
     * 加签 id
     *
     * @param activityDefId activityDefId
     * @param instanceId    instanceId
     * @param variables     variables
     */
    public void addMultiInstanceExecution(String activityDefId, String instanceId, Map<String, Object> variables) {
        managementService.executeCommand(new AddMultiInstanceExecutionCmd(activityDefId, instanceId, variables));
    }

    /**
     * 减签 id
     *
     * @param executionId 运行id
     * @param flag
     */
    public void deleteMultiInstanceExecution(String executionId, boolean flag) {
        managementService.executeCommand(new DeleteMultiInstanceExecutionCmd(executionId, flag));
    }
    /**==============================================QUERY============================*/

    /**
     * 创建查询器
     *
     * @return ProcessInstanceQuery
     */
    public ProcessInstanceQuery processInstanceQuery() {
        return runtimeService.createProcessInstanceQuery();
    }

    /**
     * 查询流程实例 Id
     *
     * @param processInstanceId id
     *
     * @return ProcessInstance
     */
    public ProcessInstance processInstanceId(String processInstanceId) {
        return processInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 批量查询流程实例
     *
     * @param processInstanceIds ids
     *
     * @return List<ProcessInstance>
     */
    public List<ProcessInstance> processInstanceIds(Set<String> processInstanceIds) {
        if(processInstanceIds.size() == 0) {
            return new ArrayList<>();
        }
        return processInstanceQuery().processInstanceIds(processInstanceIds).list();
    }

    /**
     * 通过businessKey查询流程实例
     *
     * @param businessKey businessKey
     *
     * @return ProcessInstance
     */
    public ProcessInstance processInstanceBusinessKey(String businessKey) {
        return processInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
    }

    /**
     * 查询当前任务归属流程实例
     *
     * @param taskId taskId
     *
     * @return ProcessInstance
     */
    public ProcessInstance taskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return this.processInstanceId(task.getProcessInstanceId());
    }

    /**
     * 流程实例是否结束
     *
     * @param processInstanceId id
     *
     * @return boolean
     */
    public boolean hasEnd(String processInstanceId) {
        return ObjectUtil.isNull(processInstanceId(processInstanceId));
    }


}
