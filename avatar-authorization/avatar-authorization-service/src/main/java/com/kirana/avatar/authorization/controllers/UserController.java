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
package com.kirana.avatar.authorization.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


	public UserController(UserService userService, AuthenticationManager authenticationManager, TokenProvider tokenProvider) {
		super(userService);
		this.authenticationManager = authenticationManager;
		this.userService = userService;
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
		UserInfo userInfo = (UserInfo)authentication.getPrincipal();
		UserDTO userDTO = userService.findByUserNameOrMobileNumber(loginRequest.getUserNameOrMobileNumber());
		return ResponseEntity.ok(LoginResponse
				.builder()
				.accessToken(userInfo.getUserToken())
				.userDTO(userDTO)
				.build());
	}

	@Override
	public ResponseEntity<Boolean> logout() {
		SecurityContextHolder.clearContext();
		return ResponseEntity.ok(Boolean.TRUE);
	}

	@Override
	public ResponseEntity<Map<String, Long>> sellersDailyGrowthRate(Integer depth) {
		return ResponseEntity.ok(userService.sellersDailyGrowthRate(depth));
	}

	@Override
	public ResponseEntity<Map<String, Long>> sellersMonthlyGrowthRate(Integer depth) {
		return ResponseEntity.ok(userService.sellersMonthlyGrowthRate(depth));
	}


	@Override
	public ResponseEntity<Map<String, Long>> sellersYearlyGrowthRate(Integer depth) {
		return ResponseEntity.ok(userService.sellersYearlyGrowthRate(depth));
	}

	@Override
	public ResponseEntity<Map<String, Long>> buyersDailyGrowthRate(Integer depth) {
		return ResponseEntity.ok(userService.buyersDailyGrowthRate(depth));
	}


	@Override
	public ResponseEntity<Map<String, Long>> buyersMonthlyGrowthRate(Integer depth) {
		return ResponseEntity.ok(userService.buyersMonthlyGrowthRate(depth));
	}


	@Override
	public ResponseEntity<Map<String, Long>> buyersYearlyGrowthRate(Integer depth) {
		return ResponseEntity.ok(userService.buyersYearlyGrowthRate(depth));
	}


	@Override
	public UserDTO getSellerAgentForSeller(Long sellerId) {
		return userService.getSellerAgentForSeller(sellerId);
	}


	@Override
	public UserDTO getBuyerAgentForBuyer(Long buyerId) {
		return userService.getBuyerAgentForBuyer(buyerId);
	}


	@Override
	public UserDTO getTruckDriverForSellerAgent(Long sellerAgentId) {
		return userService.getTruckDriverForSellerAgent(sellerAgentId);
	}


	@Override
	public UserDTO getSellerAgentForTruckDriver(Long truckDriverId) {
		return userService.getSellerAgentForTruckDriver(truckDriverId);
	}

	@Override
	public Map<String, Object> getWareHouseForTruckDriver(Long truckDriverId) {
		return userService.getWareHouseForTruckDriver(truckDriverId);
	}

}
