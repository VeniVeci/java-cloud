package com.zukxu.flowable.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zukxu.flowable.mapper.FlowListenerParamMapper;
import com.zukxu.flowable.model.FlowListenerParam;
import com.zukxu.flowable.service.IFlowListenerParamService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 监听器参数实现类
 * </p>
 *
 * @author xupu
 * @since 2022/6/20 10:47
 */

@Service
public class FlowListenerParamServiceImpl extends ServiceImpl<FlowListenerParamMapper, FlowListenerParam> implements IFlowListenerParamService {

    @Override
    public List<FlowListenerParam> getListByListenerId(String listenerId) {
        LambdaQueryWrapper<FlowListenerParam> flowListenerParamLambdaQueryWrapper = new LambdaQueryWrapper<>();
        flowListenerParamLambdaQueryWrapper.eq(FlowListenerParam::getListenerId, listenerId);
        return this.list(flowListenerParamLambdaQueryWrapper);
    }

}
