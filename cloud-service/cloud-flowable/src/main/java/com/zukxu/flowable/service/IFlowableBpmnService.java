package com.zukxu.flowable.service;

import com.zukxu.common.core.response.R;
import com.zukxu.common.security.service.LoginUser;

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
     * @return string
     */
    R<String> importBpmnModel(String modelId, String fileName, InputStream modelStream, LoginUser user);

    /**
     * 发布Bpmn
     *
     * @param modelId modelId
     *
     * @return str
     */
    R<String> publishBpmn(String modelId);

}
