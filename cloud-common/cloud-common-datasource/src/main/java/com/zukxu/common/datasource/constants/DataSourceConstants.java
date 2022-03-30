package com.zukxu.common.datasource.constants;

/**
 * <p>
 * 数据源相关常量
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 16:35
 */
public interface DataSourceConstants {

	/**
	 * 数据源名称
	 */
	String DS_NAME = "name";

	/**
	 * 默认数据源（master）
	 */
	String DS_MASTER = "master";
	/**
	 * 从数据源
	 */
	String DS_SLAVE = "slave";

	/**
	 * jdbc url
	 */
	String DS_JDBC_URL = "url";

	/**
	 * 用户名
	 */
	String DS_JDBC_USER_NAME = "username";

	/**
	 * 密码
	 */
	String DS_JDBC_PWD = "password";

	/**
	 * 驱动包名称
	 */
	String DS_JDBC_DRIVER_CLASS_NAME = "driver_class_name";

}
