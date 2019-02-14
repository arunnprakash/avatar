package com.kirana.avatar.common.jwt.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.dto.UserInfo.UserInfoBuilder;
import com.kirana.avatar.common.jwt.TokenProvider;
import com.kirana.avatar.common.jwt.config.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private JwtConfig jwtConfig;
	private TokenProvider tokenProvider;
	public JWTAuthorizationFilter(JwtConfig jwtConfig, TokenProvider tokenProvider) {
		super();
		this.jwtConfig = jwtConfig;
		this.tokenProvider = tokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String header = request.getHeader(jwtConfig.getHeaderName());
			if (header == null || !header.startsWith(jwtConfig.getTokenPrefix())) {
				log.debug("Token is Not Found in Header");
				chain.doFilter(request, response); // If not valid, go to the next filter.
				return;
			}
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			// go to the next filter in the filter chain
			chain.doFilter(request, response);
		} catch (Exception exp) {
			// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
			SecurityContextHolder.clearContext();
			throw exp;
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		log.debug("JwtConfig {}", jwtConfig);
		String token = request.getHeader(jwtConfig.getHeaderName());
		SecretKey secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
		if (token != null) {
			// parse the token.
			Claims claims = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token.replace(jwtConfig.getTokenPrefix(), ""))
					.getBody();
			String userName = claims.getSubject();
			if (userName != null) {
				@SuppressWarnings("unchecked")
				List<String> authorities = (List<String>) claims.get("authorities");
				@SuppressWarnings("unchecked")
				Map<String, Object> userInfoMap = (Map)claims.get("userInfo");
				List<GrantedAuthority> grantedAuthorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
				UserInfo userInfo = UserInfo.create()
						.password(userInfoMap.get("password").toString())
						.mobileNumber(userInfoMap.get("mobileNumber").toString())
						.username(userInfoMap.get("username").toString())
						.userToken(token)
						.authorities(grantedAuthorities)
						.accountExpired(false)
						.accountLocked(false)
						.credentialsExpired(false)
						.disabled(false)
						.build();
				log.debug("UserInfo {}", userInfo);
				// Create auth object
				// UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
				// It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
				return new UsernamePasswordAuthenticationToken(userInfo, null, grantedAuthorities);
			}
			log.debug("UserName not found in token {}", token);
			return null;
		}
		log.debug("Token is Not Found in Header");
		return null;
	}
}