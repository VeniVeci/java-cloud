package com.zukxu.common.datasource.config;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.zukxu.common.datasource.config.properties.DataSourceProperties;
import com.zukxu.common.datasource.support.JdbcDynamicDataSourceProvider;
import com.zukxu.common.datasource.support.LastParamDsProcessor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2020-02-06
 * <p>
 * 动态数据源切换配置
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {

	@Bean
	public DynamicDataSourceProvider dynamicDataSourceProvider(StringEncryptor stringEncryptor,
															   DataSourceProperties properties) {
		return new JdbcDynamicDataSourceProvider(properties, stringEncryptor);
	}

	@Bean
	public DsProcessor dsProcessor() {
		return new LastParamDsProcessor();
	}

}
