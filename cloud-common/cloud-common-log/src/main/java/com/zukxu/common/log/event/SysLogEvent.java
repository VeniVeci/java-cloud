package com.zukxu.common.log.event;

import com.zukxu.admin.model.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author lengleng 系统日志事件
 */
public class SysLogEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8726388525975132935L;

	public SysLogEvent(SysLog source) {
		super(source);
	}

}
