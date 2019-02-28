/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.feign.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kirana.avatar.common.feign.interceptor.FeignRequestInterceptor;
import com.kirana.avatar.common.jwt.config.JwtConfig;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@EnableFeignClients
public class FeignConfig {
	@Autowired
	private JwtConfig jwtConfig;
	
	@Bean
	public FeignRequestInterceptor feignRequestInterceptor() {
		return new FeignRequestInterceptor(jwtConfig);
	}
}
