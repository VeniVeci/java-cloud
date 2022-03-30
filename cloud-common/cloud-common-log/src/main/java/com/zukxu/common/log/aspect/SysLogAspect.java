package com.zukxu.common.log.aspect;

import cn.hutool.extra.spring.SpringUtil;
import com.zukxu.admin.model.entity.SysLog;
import com.zukxu.common.log.event.SysLogEvent;
import com.zukxu.common.log.util.LogTypeEnum;
import com.zukxu.common.log.util.SysLogUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


/**
 * <p>
 * 操作日志使用spring event异步入库
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 20:18
 */
@Aspect
@Slf4j
public class SysLogAspect {

	@Around("@annotation(sysLog)")
	@SneakyThrows
	public Object around(ProceedingJoinPoint point, com.zukxu.common.log.annotation.SysLog sysLog) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		log.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

		SysLog logVo = SysLogUtils.getSysLog();
		logVo.setTitle(sysLog.value());

		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;

		try {
			obj = point.proceed();
		} catch (Exception e) {
			logVo.setType(LogTypeEnum.ERROR.getType());
			logVo.setException(e.getMessage());
			throw e;
		} finally {
			Long endTime = System.currentTimeMillis();
			logVo.setTime(endTime - startTime);
			SpringUtil.publishEvent(new SysLogEvent(logVo));
		}

		return obj;
	}

}
