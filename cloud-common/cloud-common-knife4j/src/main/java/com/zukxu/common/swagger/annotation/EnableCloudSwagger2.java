package com.zukxu.common.swagger.annotation;

import com.zukxu.common.swagger.config.SwaggerAutoConfiguration;
import com.zukxu.common.swagger.config.properties.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 pig swagger
 *
 * @author lengleng
 * @date 2020/10/2
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({SwaggerAutoConfiguration.class})
public @interface EnableCloudSwagger2 {

}
