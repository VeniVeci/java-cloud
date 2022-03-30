package com.zukxu.admin.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zukxu.admin.api.model.dto.SysLogDTO;
import com.zukxu.admin.api.model.entity.SysLog;
import com.zukxu.admin.mapper.SysLogMapper;
import com.zukxu.admin.service.SysLogService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * v
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 23:00
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

	@Override
	public Page getLogByPage(Page page, SysLogDTO sysLog) {
		return baseMapper.selectPage(page, buildQueryWrapper(sysLog));
	}

	/**
	 * 列表查询日志
	 * @param sysLog 查询条件
	 * @return List
	 */
	@Override
	public List getLogList(SysLogDTO sysLog) {
		return baseMapper.selectList(buildQueryWrapper(sysLog));
	}

	/**
	 * 构建查询的 wrapper
	 * @param sysLog 查询条件
	 * @return LambdaQueryWrapper
	 */
	private LambdaQueryWrapper buildQueryWrapper(SysLogDTO sysLog) {
		LambdaQueryWrapper<SysLog> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysLog.getType())) {
			wrapper.eq(SysLog::getType, sysLog.getType());
		}

		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(SysLog::getCreateTime, sysLog.getCreateTime()[0]).le(SysLog::getCreateTime,
					sysLog.getCreateTime()[1]);
		}

		return wrapper;
	}

}
