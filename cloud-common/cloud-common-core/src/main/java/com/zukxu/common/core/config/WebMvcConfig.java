/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zukxu.common.core.config;

import cn.hutool.core.date.DatePattern;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;


/**
 * <p>
 *
 * </p>
 *
 * @author xupu
 * @since 2022/3/30 15:20
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = SERVLET)
public class WebMvcConfig implements WebMvcConfigurer {

	/**
	 * 增加GET请求参数中时间类型转换 {@link com.zukxu.common.core.jackson.MyJavaTimeModule}
	 * <ul>
	 * <li>HH:mm:ss -> LocalTime</li>
	 * <li>yyyy-MM-dd -> LocalDate</li>
	 * <li>yyyy-MM-dd HH:mm:ss -> LocalDateTime</li>
	 * </ul>
	 * @param registry
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
		registrar.setTimeFormatter(DatePattern.NORM_TIME_FORMATTER);
		registrar.setDateFormatter(DatePattern.NORM_DATE_FORMATTER);
		registrar.setDateTimeFormatter(DatePattern.NORM_DATETIME_FORMATTER);
		registrar.registerFormatters(registry);
	}

}
