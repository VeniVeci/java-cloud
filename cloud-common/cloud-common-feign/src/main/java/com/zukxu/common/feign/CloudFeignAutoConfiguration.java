package com.zukxu.common.feign;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.zukxu.common.feign.sentinel.SentinelAutoConfiguration;
import com.zukxu.common.feign.sentinel.extend.CloudSentinelFeign;
import com.zukxu.common.feign.sentinel.handler.CLoudUrlBlockHandler;
import com.zukxu.common.feign.sentinel.parser.CloudHeaderRequestOriginParser;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022-03-30 19:30
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelAutoConfiguration.class)
public class CloudFeignAutoConfiguration {

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    public Feign.Builder feignSentinelBuilder() {
        return CloudSentinelFeign.builder();
    }

    @Bean
    @ConditionalOnMissingBean
    public BlockExceptionHandler blockExceptionHandler() {
        return new CLoudUrlBlockHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestOriginParser requestOriginParser() {
        return new CloudHeaderRequestOriginParser();
    }

}
