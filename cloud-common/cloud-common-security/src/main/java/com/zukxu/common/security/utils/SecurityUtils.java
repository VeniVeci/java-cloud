package com.zukxu.common.security.utils;

import cn.hutool.core.util.ObjectUtil;
import com.zukxu.common.security.service.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * SecurityUtils 工具类$
 * </p>
 *
 * @author xupu
 * @since 2022/3/30$ 23:34$
 */
public class SecurityUtils {

    /**
     * 获取security的 Authentication
     * @return
     */
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public LoginUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof LoginUser) {
            return (LoginUser) principal;
        }
        return null;
    }
    public LoginUser getUser() {
        Authentication authentication = this.getAuthentication();
        if(ObjectUtil.isEmpty(authentication)) {
            return null;
        }
        return getUser(authentication);
    }
}
