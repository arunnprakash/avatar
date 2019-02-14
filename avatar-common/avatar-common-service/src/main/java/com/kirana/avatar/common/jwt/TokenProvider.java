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
package com.kirana.avatar.common.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.jwt.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * @author __ArunPrakash__
 *
 */
@Component
@RefreshScope
public class TokenProvider {

	private JwtConfig jwtConfig;

	public TokenProvider(JwtConfig jwtConfig) {
		super();
		this.jwtConfig = jwtConfig;
	}

	public String generateToken(UserInfo userInfo) {
		Long now = System.currentTimeMillis();
		SecretKey secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
		return Jwts.builder()
				.setSubject(userInfo.getUsername())
				.claim("userInfo", userInfo)
				// Convert to list of strings. 
				// This is important because it affects the way we get them back in the Gateway.
				.claim("authorities", userInfo.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + jwtConfig.getExpiration()))
				.signWith(secretKey, SignatureAlgorithm.HS512)
				.compact();
	}

	public UserInfo getUserInfo(String token) {
		Claims claims = (Claims)Jwts.parser().parse(token).getBody();
		UserInfo userInfo = (UserInfo)claims.get("userInfo");
		return userInfo;
	}

}
