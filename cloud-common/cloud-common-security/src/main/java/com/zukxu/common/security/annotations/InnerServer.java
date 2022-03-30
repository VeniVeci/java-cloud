package com.zukxu.common.security.annotations;

import java.lang.annotation.*;

/**
 * 内部服务调用无需鉴权
 */
@Documented
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface InnerServer {

    /**
     * 是否AOP统一处理
     *
     * @return true/false
     */
    boolean value() default true;

    /**
     * 需要特殊判断为空 的字段
     *
     * @return {}
     */
    String[] field() default {};

}
