package com.zukxu.common.log.util;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import com.zukxu.admin.model.entity.SysLog;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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

	public SysLog getSysLog() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects
				.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
		SysLog sysLog = new SysLog();
		sysLog.setCreateBy(Objects.requireNonNull(getUsername()));
		sysLog.setUpdateBy(Objects.requireNonNull(getUsername()));
		sysLog.setType(LogTypeEnum.NORMAL.getType());
		sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
		sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
		sysLog.setMethod(request.getMethod());
		sysLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
		sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
		sysLog.setServiceId(getClientId(request));
		return sysLog;
	}

	/**
	 * 获取客户端
	 *
	 * @return clientId
	 */
	private String getClientId(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof OAuth2Authentication) {
			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
			return auth2Authentication.getOAuth2Request().getClientId();
		}
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			BasicAuthenticationConverter basicAuthenticationConverter = new BasicAuthenticationConverter();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = basicAuthenticationConverter
					.convert(request);
			if (usernamePasswordAuthenticationToken != null) {
				return usernamePasswordAuthenticationToken.getName();
			}
		}
		return null;
	}

	/**
	 * 获取用户名称
	 *
	 * @return username
	 */
	private String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication.getName();
	}

}
