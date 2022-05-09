package com.zukxu.common.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>
 * security user$
 * </p>
 *
 * @author xupu
 * @since 2022/3/30$ 23:40$
 */
public class LoginUser extends User {

    @Getter
    private final String id;

    @Getter
    private final String deptId;


    public LoginUser(
            String id,
            String deptId,
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities
                    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.deptId = deptId;
    }

}
