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
package com.kirana.avatar.authorization.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.LoginRequest;
import com.kirana.avatar.authorization.dto.LoginResponse;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.resource.UserResource;
import com.kirana.avatar.authorization.service.UserService;
import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.jwt.TokenProvider;

/**
 * @author __ArunPrakash__
 *
 */
@RestController
public class UserController extends BaseController<UserService, UserDTO> implements UserResource {

	private AuthenticationManager authenticationManager;

	private UserService userService;

	private UserDetailsService userDetailsService;

	private TokenProvider tokenProvider;

	public UserController(UserService userService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
		super(userService);
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.userDetailsService = userDetailsService;
		this.tokenProvider = tokenProvider;
	}

	
	@Override
	public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUserNameOrMobileNumber(),
						loginRequest.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserInfo userInfo = (UserInfo)userDetailsService.loadUserByUsername(loginRequest.getUserNameOrMobileNumber());
		UserDTO userDTO = userService.findByUserNameOrMobileNumber(loginRequest.getUserNameOrMobileNumber());
		String token = tokenProvider.generateToken(userInfo);
		return ResponseEntity.ok(LoginResponse
				.builder()
				.accessToken(token)
				.userDTO(userDTO)
				.build());
	}

	@Override
	public ResponseEntity<Boolean> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
