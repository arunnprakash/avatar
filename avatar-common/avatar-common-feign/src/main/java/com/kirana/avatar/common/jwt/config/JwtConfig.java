/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.jwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author __ArunPrakash__
 *
 */
@Component
@RefreshScope
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "security.jwt", ignoreUnknownFields = false, ignoreInvalidFields = false)
public class JwtConfig {

	private String loginUri;

	private String headerName;

	private String tokenPrefix;

	private int expiration;

	private String secretKey;
	
	private String defaultPassword;
}
