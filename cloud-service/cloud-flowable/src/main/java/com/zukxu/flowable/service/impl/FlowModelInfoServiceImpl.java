package com.zukxu.flowable.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zukxu.flowable.mapper.FlowModelInfoMapper;
import com.zukxu.flowable.model.FlowModelInfo;
import com.zukxu.flowable.service.IFlowModelInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/6/10 15:35:47
 */
@Service
public class FlowModelInfoServiceImpl extends ServiceImpl<FlowModelInfoMapper, FlowModelInfo> implements IFlowModelInfoService {

    @Override
    public FlowModelInfo getModelInfoByModelKey(String modelKey) {
        if(StrUtil.isNotBlank(modelKey)) {
            return this.getOne(new LambdaQueryWrapper<FlowModelInfo>().eq(FlowModelInfo::getModelKey, modelKey));
        }
        return null;
    }

}
