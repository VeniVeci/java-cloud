package com.zukxu.common.security.feign;


import cn.hutool.core.collection.CollUtil;
import com.zukxu.common.core.constant.SecurityConstants;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.commons.security.AccessTokenContextRelay;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.Collection;

/**
 * <p>
 * 扩展OAuth2FeignRequestInterceptor
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 23:17
 */
@Slf4j
public class CloudFeignClientInterceptor extends OAuth2FeignRequestInterceptor {

	private final OAuth2ClientContext oAuth2ClientContext;

	private final AccessTokenContextRelay accessTokenContextRelay;

	/**
	 * Default constructor which uses the provided OAuth2ClientContext and Bearer tokens
	 * within Authorization header
	 * @param oAuth2ClientContext provided context
	 * @param resource type of resource to be accessed
	 * @param accessTokenContextRelay
	 */
	public CloudFeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext,
									   OAuth2ProtectedResourceDetails resource,
                                       AccessTokenContextRelay accessTokenContextRelay) {
		super(oAuth2ClientContext, resource);
		this.oAuth2ClientContext = oAuth2ClientContext;
		this.accessTokenContextRelay = accessTokenContextRelay;
	}

	/**
	 * Create a template with the header of provided name and extracted extract
	 * 1. 如果使用非web 请求，header 区别
	 * 2. 根据authentication 还原请求token
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
		if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_Y)) {
			return;
		}

		accessTokenContextRelay.copyToken();
		if (oAuth2ClientContext != null && oAuth2ClientContext.getAccessToken() != null) {
			super.apply(template);
		}
	}

}
