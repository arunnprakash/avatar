package com.kirana.avatar.authorization.config;

import static java.util.Collections.emptyList;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.jwt.config.JwtConfig;
import com.kirana.avatar.common.jwt.TokenProvider;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private JwtConfig jwtConfig;
	private TokenProvider tokenProvider;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, TokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.jwtConfig = jwtConfig;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			log.debug("Getting UserCredentials");
			// 1. Get credentials from request
			UserCredentials creds = new ObjectMapper().readValue(req.getInputStream(), UserCredentials.class);
			log.debug("UserCredentials {}", creds);
			// 2. Create auth object (contains credentials) which will be used by auth manager
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), emptyList());
			// 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
			return authenticationManager.authenticate(authToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Upon successful authentication, generate a token.
	// The 'auth' passed to successfulAuthentication() is the current authenticated user.
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		log.debug("SecretKey {}", jwtConfig.getSecretKey());
		UserInfo userInfo = (UserInfo)auth.getPrincipal();
		String token = tokenProvider.generateToken(userInfo);
		String bearerToken = String.format("%s %s", jwtConfig.getTokenPrefix().trim(), token);
		log.debug("Bearer-Token {}", bearerToken);
		res.addHeader(jwtConfig.getHeaderName(), bearerToken);
	}
	
	// A (temporary) class just to represent the user credentials
	@ToString
	@Getter
	private static class UserCredentials {
		private String username;
		private String password;
	}
}