package com.zukxu.flowable.handler;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>
 * 流程任务处理器
 * </p>
 *
 * @author xupu
 * @since 2022/2/28 15:48
 */
@Slf4j
@Component
public class TaskHandler extends FlowableFactory {

    /**
     * 认领任务
     * claim会检查该任务是否已经被认领
     * 被认领则会抛出ActivitiTaskAlreadyClaimedException
     * 而setAssignee不会进行这样的检查
     *
     * @param taskId id
     * @param userId userId
     */
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
        log.info("-----------任务ID：{},已签收{}-----------", taskId, userId);
    }

    /**
     * 设置节点处理人
     *
     * @param taskId id
     * @param userId userId
     */
    public void setAssignee(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
        log.info("-----------任务ID：{},已设置处理人{}-----------", taskId, userId);
    }

    /**
     * 设置任务实际拥有者
     * 可以将任务委托给其他人setAssignee处理，但是实际上的用户还是Owner
     *
     * @param taskId id
     * @param userId userId
     */
    public void setOwner(String taskId, String userId) {
        taskService.setOwner(taskId, userId);
    }

    /**
     * 退还任务
     *
     * @param taskId id
     */
    public void unclaim(String taskId) {
        taskService.unclaim(taskId);
        log.info("-----------退还任务：{}-----------", taskId);
    }

    /**
     * 完成任务
     *
     * @param taskId id
     */
    public void complete(String taskId) {
        this.complete(taskId, Collections.emptyMap());
    }

    /**
     * @param variables variables
     */
    public void complete(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables, false);
    }

    /**
     * @param localScope scope
     */
    public void complete(String taskId, Map<String, Object> variables, boolean localScope) {
        taskService.complete(taskId, variables, localScope);
        log.info("-----------任务ID：{},已完成-----------", taskId);
    }

    /**
     * 委派任务给用户
     *
     * @param taskId id
     * @param userId userId
     */
    public void delegate(String taskId, String userId) {
        taskService.delegateTask(taskId, userId);
        log.info("-----------任务ID：{},已委派{}-----------", taskId, userId);
    }

    /**
     * 完成委派任务
     *
     * @param taskId id
     */
    public void resolveTask(String taskId) {
        this.resolveTask(taskId, Collections.emptyMap());
    }

    /**
     * @param variables variables
     */
    public void resolveTask(String taskId, Map<String, Object> variables) {
        taskService.resolveTask(taskId, variables);
        log.info("-----------任务ID：{},已完成委派-----------", taskId);
        log.info("-----------任务参数：{}-----------", variables);
    }

    /**
     * 删除任务
     *
     * @param taskId id
     */
    public void delete(String taskId) {
        this.deleteWithReason(taskId, StrUtil.EMPTY);
    }

    /**
     * @param reason 删除原因
     */
    public void deleteWithReason(String taskId, String reason) {
        taskService.deleteTask(taskId, reason);
        log.info("-----------任务ID：{},已删除, {}-----------", taskId, reason);
    }

    /**
     * 设置多个候选处理人
     *
     * @param taskId id
     * @param userId userId
     */
    public void addCandidateUser(String taskId, String userId) {
        taskService.addCandidateUser(taskId, userId);
    }

    /**
     * 添加任务批注
     *
     * @param taskId          id
     * @param processInstance processInstance
     * @param message         message
     *
     * @return Comment
     */
    public Comment addComment(String taskId, String processInstance, String type, String message) {
        return taskService.addComment(taskId, processInstance, type, message);
    }

    /**
     * 获取任务批注
     *
     * @param taskId id
     *
     * @return List<Comment>
     */
    public List<Comment> getTaskComments(String taskId) {
        return taskService.getTaskComments(taskId);
    }

    /**
     * 撤回处理
     *
     * @param processInstanceId processInstanceId
     * @param currentActivityId 当前运行id
     * @param newActivityId     撤回后执行的id
     */
    public void withdraw(String processInstanceId, String currentActivityId, String newActivityId) {
        runtimeService.createChangeActivityStateBuilder()
                      .processInstanceId(processInstanceId)
                      .moveActivityIdTo(currentActivityId, newActivityId)
                      .changeState();
    }

    /**
     * 设置流程变量 局部
     *
     * @param taskId
     * @param variableName
     * @param value
     */
    public void setVariableLocal(String taskId, String variableName, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(variableName, value);
        this.setVariablesLocal(taskId, map);
    }

    public void setVariablesLocal(String taskId, Map<String, ?> variables) {
        taskService.setVariablesLocal(taskId, variables);
    }

    /**===========================================QUERY=============================*/
    /**
     * 创建任务查询器
     *
     * @return TaskQuery
     */
    public TaskQuery taskQuery() {
        return taskService.createTaskQuery();
    }

    /**
     * 根据参数构建查询器
     *
     * @param args 查询参数
     *
     * @return TaskQuery
     */
    public TaskQuery buildTaskQueryByVariables(Map<String, Object> args) {
        TaskQuery tq = taskQuery();
        Optional.ofNullable(args).ifPresent(t -> t.forEach(tq::processVariableValueEquals));
        return tq;
    }

    public Task queryTaskById(String taskId) {
        return taskQuery().taskId(taskId).singleResult();
    }

    public Task queryTaskByInsId(String processInstanceId) {
        List<Task> list = taskQuery().processInstanceId(processInstanceId).active().list();
        return null != list && list.size() > 0 ? list.get(0) : null;
    }

    public List<Task> queryTaskListByInsId(String processInstanceId) {
        return taskQuery().processInstanceId(processInstanceId).list();
    }

    public List<Task> listActiveTaskByInsId(String processInstanceId) {
        List<Task> list = new ArrayList<>();
        TaskQuery taskQuery = taskQuery().processInstanceId(processInstanceId).active();

        long count = taskQuery.count();
        //多实例情况，当前活动任务不止一条数据
        if(count > 1) {
            list.addAll(taskQuery.list());
        } else {
            list.add(taskQuery.singleResult());
        }
        return list;
    }

    /**
     * 分页查询用户的任务
     *
     * @param userId userId
     * @param start  start
     * @param limit  limit
     *
     * @return List<Task>
     */
    public List<Task> taskCandidateUser(String userId, int start, int limit) {
        return taskQuery().taskCandidateUser(userId)
                          .orderByTaskPriority().desc()
                          .orderByTaskCreateTime().asc()
                          .listPage(start, limit);
    }

    /**
     * 分页查询用户参与的任务
     *
     * @param userId userId
     * @param start  start
     * @param limit  limit
     *
     * @return List<Task>
     */
    public List<Task> taskAssignee(String userId, int start, int limit) {
        return taskQuery().taskAssignee(userId)
                          .orderByTaskPriority().desc()
                          .orderByTaskCreateTime().asc()
                          .listPage(start, limit);
    }

    public List<Task> taskCandidateOrAssigned(String userId) {
        return taskQuery().taskCandidateOrAssigned(userId)
                          .orderByTaskPriority().desc()
                          .orderByTaskCreateTime().asc()
                          .list();
    }

    public List<Task> taskCandidateUserByCondition(String candidateUser, Map<String, Object> variables, int start, int limit) {
        return buildTaskQueryByVariables(variables).taskCandidateUser(candidateUser)
                                                   .orderByTaskPriority().desc()
                                                   .orderByTaskCreateTime().asc()
                                                   .listPage(start, limit);
    }

    public List<Task> taskAssigneeByCondition(String assignee, Map<String, Object> variables, int start, int limit) {
        return buildTaskQueryByVariables(variables).taskAssignee(assignee).orderByTaskPriority().desc()
                                                   .orderByTaskCreateTime().asc()
                                                   .listPage(start, limit);
    }

    public List<Task> taskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables, int start, int limit) {
        return buildTaskQueryByVariables(variables).taskCandidateOrAssigned(userId).orderByTaskPriority().desc()
                                                   .orderByTaskCreateTime().asc()
                                                   .listPage(start, limit);
    }


    public List<Task> taskAssigneeByTaskQuery(String assignee, TaskQuery query, int start, int limit) {
        return query.taskAssignee(assignee).orderByTaskPriority().desc()
                    .orderByTaskCreateTime().asc()
                    .listPage(start, limit);
    }

    /**
     * 查询用户参与的任务数量
     *
     * @param candidateUser userId
     *
     * @return long
     */
    public long countTaskCandidateUser(String candidateUser) {
        return this.countTaskCandidateUserByCondition(candidateUser, MapUtil.empty());
    }

    /**
     * 查询用户完成的任务数量
     *
     * @param assignee userId
     *
     * @return long
     */
    public long countTaskAssignee(String assignee) {
        return this.countTaskAssigneeByCondition(assignee, MapUtil.empty());
    }

    /**
     * 查询用户涉及到的任务数量
     *
     * @param userId userId
     *
     * @return long
     */
    public long countTaskCandidateOrAssigned(String userId) {
        return this.countTaskCandidateOrAssignedByCondition(userId, MapUtil.empty());
    }

    public long countTaskCandidateUserByCondition(String candidateUser, Map<String, Object> variables) {
        return buildTaskQueryByVariables(variables).taskCandidateUser(candidateUser)
                                                   .orderByTaskPriority().desc()
                                                   .orderByTaskCreateTime().asc()
                                                   .count();
    }

    public long countTaskAssigneeByCondition(String assignee, Map<String, Object> variables) {
        return buildTaskQueryByVariables(variables).taskAssignee(assignee).orderByTaskPriority().desc()
                                                   .orderByTaskCreateTime().asc()
                                                   .count();
    }

    public long countTaskCandidateOrAssignedByCondition(String userId, Map<String, Object> variables) {
        return buildTaskQueryByVariables(variables).taskCandidateOrAssigned(userId)
                                                   .orderByTaskPriority().desc()
                                                   .orderByTaskCreateTime().asc()
                                                   .count();
    }


    public long countTaskAssigneeByTaskQuery(String assignee, TaskQuery query) {
        return query.taskAssignee(assignee)
                    .orderByTaskPriority().desc()
                    .orderByTaskCreateTime().asc()
                    .count();
    }


    public String findBusinessKeyByTaskId(String taskId) {
        Task task = taskQuery().taskId(taskId).singleResult();
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        if(pi != null) {
            return pi.getBusinessKey();
        }

        return null;
    }

    public String findVariableByTaskId(String taskId, String variableName) {
        Object value = taskService.getVariable(taskId, variableName);
        return String.valueOf(value);
    }

    /**=========================HistoricTaskInstanceQuery=====================*/
    /**
     * 创建查询器
     *
     * @return HistoricTaskInstanceQuery
     */
    public HistoricTaskInstanceQuery hisTaskQuery() {
        return historyService.createHistoricTaskInstanceQuery();
    }

    /**
     * 查询待办历史任务
     *
     * @param instanceId 流程实例id
     *
     * @return HistoricTaskInstance
     */
    public HistoricTaskInstance todoTask(String instanceId) {
        return hisTaskQuery().processInstanceId(instanceId).unfinished().singleResult();
    }

    /**
     * 查询已完成历史任务
     *
     * @param taskId id
     *
     * @return HistoricTaskInstance
     */
    public HistoricTaskInstance finishedTask(String taskId) {
        return hisTaskQuery().taskId(taskId).singleResult();
    }

    /**
     * instanceId 查询任务列表
     *
     * @param instanceId 流程实例id
     *
     * @return List<HistoricTaskInstance>
     */
    public List<HistoricTaskInstance> listByInstanceId(String instanceId) {
        return hisTaskQuery().processInstanceId(instanceId).orderByTaskCreateTime().desc().list();

    }

    /**
     * 分页查询任务列表
     *
     * @param start start
     * @param limit limit
     *
     * @return List<HistoricTaskInstance>
     */
    public List<HistoricTaskInstance> pageListByInstanceId(String instanceId, int start, int limit) {
        return hisTaskQuery()
                .processInstanceId(instanceId)
                .orderByTaskCreateTime()
                .desc().listPage(start, limit);

    }

}
