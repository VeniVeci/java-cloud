package com.zukxu.flowable.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zukxu.flowable.mapper.FlowListenerMapper;
import com.zukxu.flowable.model.FlowListener;
import com.zukxu.flowable.model.FlowListenerParam;
import com.zukxu.flowable.service.IFlowListenerParamService;
import com.zukxu.flowable.service.IFlowListenerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 监听器服务实现类
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:47
 */
@Service
@AllArgsConstructor
public class FlowListenerServiceImpl extends ServiceImpl<FlowListenerMapper, FlowListener> implements IFlowListenerService {

    //@formatter:off
    final private IFlowListenerParamService flowListenerParamService;
    //@formatter:on

    @Override
    public List<FlowListener> getListAndParams(FlowListener flowListener) {
        List<FlowListener> listeners = this.getList(flowListener);
        if(CollectionUtil.isNotEmpty(listeners)) {
            listeners.forEach(listener -> {
                List<FlowListenerParam> listenerParams = flowListenerParamService.getListByListenerId(listener.getId());
                listener.setFlowListenerParamList(listenerParams);
            });
        }
        return listeners;
    }

    @Override
    public List<FlowListener> getList(FlowListener flowListener) {
        LambdaQueryWrapper<FlowListener> flowListenerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        flowListenerLambdaQueryWrapper.eq(StrUtil.isNotBlank(flowListener.getListenerType()),
                                          FlowListener::getListenerType,
                                          flowListener.getListenerType())
                                      .like(StrUtil.isNotBlank(flowListener.getName()), FlowListener::getName, flowListener.getName());
        return this.list(flowListenerLambdaQueryWrapper);
    }

    @Override
    public Boolean deleteById(String id) {
        LambdaQueryWrapper<FlowListenerParam> flowListenerParamLambdaQueryWrapper = new LambdaQueryWrapper<>();
        flowListenerParamLambdaQueryWrapper.eq(FlowListenerParam::getListenerId, id);
        flowListenerParamService.remove(flowListenerParamLambdaQueryWrapper);
        return this.removeById(id);
    }

    @Override
    public FlowListener getFlowListenerByNameAndType(String type, String name) {
        LambdaQueryWrapper<FlowListener> flowListenerLambdaQueryWrapper = new LambdaQueryWrapper<>();
        flowListenerLambdaQueryWrapper.eq(FlowListener::getType, type)
                                      .eq(FlowListener::getName, name);
        return getOne(flowListenerLambdaQueryWrapper);
    }

    @Override
    public FlowListener getFlowListenerById(String id) {
        FlowListener flowListener = this.getById(id);
        List<FlowListenerParam> list = flowListenerParamService.getListByListenerId(id);
        flowListener.setFlowListenerParamList(list);
        return flowListener;
    }


}
