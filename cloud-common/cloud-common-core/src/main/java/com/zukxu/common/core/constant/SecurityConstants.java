package com.zukxu.common.core.constant;

/**
 * <p>
 * security 常量池
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 16:10
 */
public interface SecurityConstants {
    /**
     * 验证码有效期,默认 60秒
     */
    long CODE_TIME = 60;

    /**
     * 验证码长度
     */
    String CODE_SIZE = "6";
    /**
     * token 前缀
     */
    String TOKEN_PREFIX = "Bearer ";
    /**
     * token 存储header
     */
    String TOKEN_HEADER = "Authorization";
    /**
     * token 过期时间 5*60*60*100 ms
     */
    long TOKEN_EXPIRE_TIME = 18000_000;
    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";
}
