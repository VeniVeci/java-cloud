package com.zukxu.common.log.event;

import com.zukxu.admin.api.model.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 系统日志事件
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 22:40
 */
public class SysLogEvent extends ApplicationEvent {

    private static final long serialVersionUID = -8726388525975132935L;

    public SysLogEvent(SysLog source) {
        super(source);
    }

}
