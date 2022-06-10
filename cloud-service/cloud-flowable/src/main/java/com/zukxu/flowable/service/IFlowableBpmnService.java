package com.zukxu.flowable.service;

import com.zukxu.common.core.response.R;
import com.zukxu.common.security.service.LoginUser;
import com.zukxu.flowable.model.FlowModelInfo;
import com.zukxu.flowable.model.vo.ModelInfoVo;
import org.flowable.engine.repository.Deployment;

import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 9:46:40
 */
public interface IFlowableBpmnService {

    /**
     * 导入bpmn模型
     *
     * @param modelId     模型ID
     * @param fileName    文件名称
     * @param modelStream 模型文件流
     * @param user        登录用户
     *
     * @return String
     */
    R<String> importBpmnModel(String modelId, String fileName, InputStream modelStream, LoginUser user);

    /**
     * 发布Bpmn
     *
     * @param modelId modelId
     *
     * @return String
     */
    R<String> publishBpmn(String modelId);

    /**
     * 部署流程
     *
     * @param modelInfo 模型的扩展信息
     *
     * @return 部署信息
     */
    R<Deployment> deployBpmn(FlowModelInfo modelInfo);

    /**
     * 停止Bpmn
     *
     * @param modelId modelId
     *
     * @return String
     */
    R<String> stopBpmn(String modelId);

    /**
     * 根据modelId获取xml信息
     *
     * @param modelId modelId
     *
     * @return ModelInfoVO
     */
    ModelInfoVo loadBpmnXmlByModelId(String modelId);

    /**
     * @param modelKey modelKey
     */
    ModelInfoVo loadBpmnXmlByModelKey(String modelKey);

}
