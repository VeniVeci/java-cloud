package com.zukxu.common.datasource.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 16:36
 */
@Data
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {

	/**
	 * 驱动类型
	 */
	private String driverClassName;

	/**
	 * jdb curl
	 */
	private String url;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 查询数据源的SQL
	 */
	private String queryDsSql = "select * from gen_datasource_conf where del_flag = 0";

}
