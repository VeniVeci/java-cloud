package com.zukxu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.Session;

/**
 * <p>
 * security config
 * </p>
 *
 * @author xupu
 * @since 2022/7/5 15:30
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {

}
