package com.zukxu.common.core.constant;

/**
 * <p>
 * 通用常量池
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 16:10
 */
public interface CommonConstants {

    /**
     * 逻辑删除值 正常
     */
    int STATUS_NORMAL = 0;
    /**
     * 逻辑删除值 删除
     */
    int STATUS_DEL = 1;

    /**
     * 菜单树根节点
     */
    Long MENU_TREE_ROOT_ID = -1L;

    /**==============================================*/
    /**
     * 认证服务的 SERVICE NAME
     */
    String AUTH_SERVICE = "pig-auth";

    /**
     * UMPS模块
     */
    String UMPS_BIZ_SERVICE = "cloud-upms-biz";

}
