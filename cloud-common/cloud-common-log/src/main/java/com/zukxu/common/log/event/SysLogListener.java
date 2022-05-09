package com.zukxu.common.log.event;

import com.zukxu.admin.api.feign.RemoteLogService;
import com.zukxu.admin.api.model.entity.SysLog;
import com.zukxu.common.core.constant.SecurityConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 * 异步监听日志事件
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 22:41
 */
@Slf4j
@RequiredArgsConstructor
public class SysLogListener {

    private final RemoteLogService remoteLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        remoteLogService.saveLog(sysLog, SecurityConstants.FROM_Y);
    }

}
