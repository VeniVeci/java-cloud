package com.zukxu.common.core.response;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * <p>
 * 返回类注解封装
 * 继承 @ResponseBody 注解
 * </p>
 *
 * @author xupu
 * @since 2022-05-09 9:45
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@ResponseBody
public @interface RResponse {
}
