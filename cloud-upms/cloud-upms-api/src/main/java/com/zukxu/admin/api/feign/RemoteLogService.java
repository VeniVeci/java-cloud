package com.zukxu.admin.api.feign;

import com.zukxu.admin.api.model.entity.SysLog;
import com.zukxu.common.core.constant.CommonConstants;
import com.zukxu.common.core.constant.SecurityConstants;
import com.zukxu.common.core.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


/**
 * <p>
 * 远程调用日志服务接口
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 22:48
 */
@Component
@FeignClient(contextId = "remoteLogService", value = CommonConstants.UMPS_BIZ_SERVICE)
public interface RemoteLogService {

    /**
     * 保存日志
     *
     * @param sysLog 日志实体
     * @param from   内部调用标志
     *
     * @return success、false
     */
    @PostMapping("/log")
    R<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);

}
