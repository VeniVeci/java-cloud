package com.zukxu.common.core.constant;

/**
 * <p>
 * 缓存常量池
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 16:10
 */
public interface CacheConstants {

    /**
     * oauth 缓存前缀
     */
    String MODULE_OAUTH_ACCESS = "cloud_oauth:access:";

    /**
     * oauth 缓存令牌前缀
     */
    String MODULE_OAUTH_TOKEN = "cloud_oauth:token:";

    /**
     * oauth 客户端信息
     */
    String MODULE_OAUTH_CLIENT_DETAILS = "cloud_oauth:client:details";

    /**
     * 验证码前缀
     */
    String CAPTCHA_KEY = "captcha:";

    /**
     * 菜单信息缓存
     */
    String MENU_DETAILS = "menu_details";

    /**
     * 用户信息缓存
     */
    String USER_DETAILS = "user_details";

    /**
     * 字典信息缓存
     */
    String DICT_DETAILS = "dict_details";

}
