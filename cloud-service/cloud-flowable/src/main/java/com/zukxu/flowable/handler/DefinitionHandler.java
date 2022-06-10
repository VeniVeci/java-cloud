package com.zukxu.flowable.handler;


import cn.hutool.core.util.StrUtil;
import com.zukxu.flowable.constants.FlowConstants;
import org.flowable.engine.repository.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * <p>
 * 流程定义处理器
 * </p>
 *
 * @author xupu
 * @since 2022/2/28 15:48
 */
@Component
public class DefinitionHandler extends FlowableFactory {

    /**
     * 创建部署所需builder
     *
     * @return DeploymentBuilder
     */
    public DeploymentBuilder createDeployment() {
        return repositoryService.createDeployment();
    }

    /**
     * 创建deploy查询器
     *
     * @return DeploymentQuery
     */
    public DeploymentQuery deploymentQuery() {
        return repositoryService.createDeploymentQuery();
    }

    /**
     * 创建definition查询器
     *
     * @return ProcessDefinitionQuery
     */
    public ProcessDefinitionQuery processDefinitionQuery() {
        return repositoryService.createProcessDefinitionQuery();
    }

    /**
     * bpmn文件部署
     *
     * @param bpmnFileUrl 文件url
     *
     * @return Deployment
     */
    public Deployment deploy(String bpmnFileUrl) {
        return createDeployment().addClasspathResource(bpmnFileUrl).deploy();
    }

    /**
     * bpmn/png文件部署
     *
     * @param url    文件路径
     * @param pngUrl 图片路径
     *
     * @return Deployment
     */
    public Deployment deploy(String url, String pngUrl) {
        return deploy(url, pngUrl, StrUtil.EMPTY, StrUtil.EMPTY);
    }

    /**
     * @param name     部署名称
     * @param category 部署分类
     *
     * @return Deployment
     */
    public Deployment deploy(String url, String pngUrl, String name, String category) {
        return createDeployment().addClasspathResource(url).addClasspathResource(pngUrl).name(name).category(category).deploy();
    }

    /**
     * 压缩文件部署时设置参数
     *
     * @param name           部署名称
     * @param tenantId       tenantId
     * @param category       分类
     * @param zipInputStream 压缩文件
     *
     * @return Deployment
     */
    public Deployment deploy(String name, String tenantId, String category, ZipInputStream zipInputStream) {
        return createDeployment().addZipInputStream(zipInputStream).name(name).category(category).tenantId(tenantId).deploy();
    }

    /**
     * 放置在项目路径下部署
     *
     * @param url      文件路径
     * @param name     部署名称
     * @param category 部署分类
     *
     * @return Deployment
     */
    public Deployment deploy(String url, String name, String category) {
        return createDeployment().addClasspathResource(url).name(name).category(category).deploy();
    }

    /**
     * 上传部署
     *
     * @param in InputStream
     *
     * @return Deployment
     */
    public Deployment deploy(String name, String tenantId, String category, InputStream in) {
        return createDeployment().addInputStream(name + FlowConstants.BPMN_SUFFIX, in)
                                 .name(name)
                                 .tenantId(tenantId)
                                 .category(category)
                                 .deploy();
    }

    /**
     * 查询部署
     *
     * @param deploymentName name
     *
     * @return Deployment
     */
    public Deployment deployName(String deploymentName) {
        List<Deployment> list = repositoryService
                .createDeploymentQuery()
                .deploymentName(deploymentName).list();
        Assert.notNull(list, "list must not be null");
        return list.get(0);
    }

    /**
     * 根据key查询
     *
     * @param processDefinitionKey key
     *
     * @return ProcessDefinition
     */
    public ProcessDefinition queryByProcessDefinitionKey(String processDefinitionKey) {
        return processDefinitionQuery().processDefinitionKey(processDefinitionKey).active().singleResult();
    }

    /**
     * 是否存在已部署流程定义
     *
     * @param processDefinitionKey key
     *
     * @return boolean
     */
    public boolean isExist(String processDefinitionKey) {
        ProcessDefinitionQuery processDefinitionQuery = processDefinitionQuery().processDefinitionKey(processDefinitionKey);
        long count = processDefinitionQuery.count();
        return count > 0;
    }

    /**
     * 设置分配用户或者启动用户
     *
     * @param processDefinitionKey key
     * @param userId               id
     */
    public void addCandidateStarterUser(String processDefinitionKey, String userId) {
        repositoryService.addCandidateStarterUser(processDefinitionKey, userId);
    }

}
