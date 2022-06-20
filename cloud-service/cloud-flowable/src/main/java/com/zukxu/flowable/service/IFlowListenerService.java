package com.zukxu.flowable.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zukxu.flowable.model.FlowListener;

import java.util.List;


/**
 * <p>
 * 监听器服务类
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:46
 */
public interface IFlowListenerService extends IService<FlowListener> {

    /**
     * 查询监听器 并把参数查出来
     *
     * @param flowListener 参数
     *
     * @return
     */
    List<FlowListener> getListAndParams(FlowListener flowListener);

    /**
     * 查询监听器
     *
     * @param flowListener 监听器
     *
     * @return
     */
    List<FlowListener> getList(FlowListener flowListener);

    Boolean deleteById(String id);

    /**
     * 通过类型和名称查询对象
     *
     * @param type 类型
     * @param name 名称
     *
     * @return
     */
    FlowListener getFlowListenerByNameAndType(String type, String name);

    FlowListener getFlowListenerById(String id);

}
