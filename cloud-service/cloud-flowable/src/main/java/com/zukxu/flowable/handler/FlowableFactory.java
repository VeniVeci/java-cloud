package com.zukxu.flowable.handler;

import lombok.Getter;
import org.flowable.engine.*;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * flowable 引擎注入封装
 * </p>
 *
 * @author xupu
 * @since 2021/12/16 10:51
 */
@Getter
@Component
public class FlowableFactory {

    //@formatter:off
    @Resource
    protected ProcessEngine processEngine;
    @Resource
    protected RepositoryService repositoryService;
    @Resource
    protected RuntimeService runtimeService;
    @Resource
    protected TaskService taskService;
    @Resource
    protected HistoryService historyService;
    @Resource
    protected FormService formService;
    @Resource
    protected ModelService modelService;
    @Resource
    protected IdentityService identityService;
    @Resource
    protected ManagementService managementService;
    //@formatter:on
}
