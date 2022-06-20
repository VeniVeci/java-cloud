package com.zukxu.flowable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zukxu.flowable.model.FlowListenerParam;

import java.util.List;

/**
 * <p>
 * 监听器参数服务类
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:46
 */
public interface IFlowListenerParamService extends IService<FlowListenerParam> {

    /**
     * 通过监听器id获取参数列表
     *
     * @param listenerId 监听器id
     *
     * @return
     */
    List<FlowListenerParam> getListByListenerId(String listenerId);

}
