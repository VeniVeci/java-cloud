package com.zukxu.common.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.zukxu.common.log.model.SysLogOpRecord;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * <p>
 * 系统日志工具类
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 20:23
 */
@UtilityClass
public class SysLogUtils {

    public SysLogOpRecord getSysLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLogOpRecord sysLog = new SysLogOpRecord();
        sysLog.setOpUserName(Objects.requireNonNull(getUsername()));
        sysLog.setIp(ServletUtil.getClientIP(request));
        LocalDateTime now = LocalDateTime.now();
        sysLog.setBgnTime(now).setYear(now.getYear()).setMonth(now.getMonthValue()).setDay(now.getDayOfMonth());
        SysLogOpRecord.ReqContent reqContent = sysLog.new ReqContent();
        reqContent.setUrl(URLUtil.getPath(request.getRequestURI()))
                  .setParams(HttpUtil.toParams(request.getParameterMap()));
        sysLog.setReqContent(reqContent);
        return sysLog;
    }

    /**
     * 从security中获取用户名称
     *
     * @return username
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        return authentication.getName();
    }

}
