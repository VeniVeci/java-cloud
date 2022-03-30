package com.zukxu.common.log.annotation;

import java.lang.annotation.*;


/**
 * <p>
 * 操作日志注解
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 20:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	/**
	 * 描述
	 *
	 * @return {String}
	 */
	String value();

}
