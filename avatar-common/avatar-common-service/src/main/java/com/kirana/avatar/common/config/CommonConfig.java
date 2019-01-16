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
package com.kirana.avatar.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.kirana.avatar.common.feign.config.FeignConfig;
import com.kirana.avatar.common.jpa.config.JpaConfig;
import com.kirana.avatar.common.jwt.config.JwtConfig;
import com.kirana.avatar.common.swagger.config.SwaggerConfig;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@ComponentScan({"com.kirana.avatar"})
@EnableConfigurationProperties({JwtConfig.class})
@Import({JpaConfig.class, FeignConfig.class, SwaggerConfig.class})
public class CommonConfig {

}
