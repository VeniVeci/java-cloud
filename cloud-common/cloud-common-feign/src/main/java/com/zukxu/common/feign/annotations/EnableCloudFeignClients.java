package com.zukxu.common.feign.annotations;

import org.springframework.cloud.openfeign.CloudFeignClientsRegistrar;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 19:31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
@Import(CloudFeignClientsRegistrar.class)
public @interface EnableCloudFeignClients {
}
