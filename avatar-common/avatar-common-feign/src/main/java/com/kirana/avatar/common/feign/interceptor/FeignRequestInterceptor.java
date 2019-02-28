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
package com.kirana.avatar.common.feign.interceptor;

import org.springframework.security.core.context.SecurityContextHolder;

import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.jwt.config.JwtConfig;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

	private JwtConfig jwtConfig;

	public FeignRequestInterceptor(JwtConfig jwtConfig) {
		super();
		this.jwtConfig = jwtConfig;
	}

	/* (non-Javadoc)
	 * @see feign.RequestInterceptor#apply(feign.RequestTemplate)
	 */
	@Override
	public void apply(RequestTemplate template) {
		if (template.headers().containsKey(jwtConfig.getHeaderName())) {
			log.warn("The Authorization token has been already set");
		} else if (SecurityContextHolder.getContext().getAuthentication() == null) {
			log.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
		} else {
			UserInfo userInfo = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			log.debug("Constructing Header {} for Token {}", jwtConfig.getHeaderName(),
					userInfo.getUserToken());
			template.header(jwtConfig.getHeaderName(), userInfo.getUserToken());
		}
		log.info("Authorization token interceptor completed");
	}


}
