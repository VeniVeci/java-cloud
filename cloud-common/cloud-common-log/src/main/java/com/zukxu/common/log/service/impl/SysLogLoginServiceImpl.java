package com.zukxu.common.log.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.zukxu.common.core.base.QueryEntity;
import com.zukxu.common.log.mapper.SysLogLoginMapper;
import com.zukxu.common.log.model.SysLogLogin;
import com.zukxu.common.log.service.SysLogLoginService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志
 * </p>
 *
 * @author xupu
 * @since 2022/6/21 10:47:
 */
@Service
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements SysLogLoginService {

    @Override
    public List<SysLogLogin> selectList(QueryEntity query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        QueryWrapper<SysLogLogin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(StrUtil.isNotBlank(query.getKeyWord()), SysLogLogin::getModule, query.getKeyWord());
        Map<String, Object> params = query.getParams();
        if(ObjectUtil.isNotEmpty(params)) {
            params.forEach(wrapper::eq);
        }
        return this.baseMapper.selectList(wrapper);
    }

}



