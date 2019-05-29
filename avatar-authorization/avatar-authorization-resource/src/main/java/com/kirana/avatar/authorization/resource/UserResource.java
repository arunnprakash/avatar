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
package com.kirana.avatar.authorization.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import javax.validation.Valid;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.authorization.dto.LoginRequest;
import com.kirana.avatar.authorization.dto.LoginResponse;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __ArunPrakash__
 *
 */
@TypeScriptEndpoint("UserService")
@TypeScriptTemplatesConfiguration(useSuffixes=false)
@RequestMapping(value= {"/api/user"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface UserResource extends BaseResource<UserDTO> {

	@PostMapping(value= {"/login"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest);

	@GetMapping(value= {"/logout"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> logout();

	@GetMapping(value= {"/sellers/daily/growth-rate/{depth}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> sellersDailyGrowthRate(@PathVariable("depth") Integer depth);
	
	@GetMapping(value= {"/sellers/monthly/growth-rate/{depth}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> sellersMonthlyGrowthRate(@PathVariable("depth") Integer depth);
	
	@GetMapping(value= {"/sellers/yearly/growth-rate/{depth}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> sellersYearlyGrowthRate(@PathVariable("depth") Integer depth);

	@GetMapping(value= {"/buyers/daily/growth-rate/{depth}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> buyersDailyGrowthRate(@PathVariable("depth") Integer depth);
	
	@GetMapping(value= {"/buyers/monthly/growth-rate/{depth}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> buyersMonthlyGrowthRate(@PathVariable("depth") Integer depth);
	
	@GetMapping(value= {"/buyers/yearly/growth-rate/{depth}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Long>> buyersYearlyGrowthRate(@PathVariable("depth") Integer depth);
	
	@GetMapping(value= {"/seller/{sellerId}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public UserDTO getSellerAgentForSeller(@PathVariable("sellerId") Long sellerId);
	
	@GetMapping(value= {"/buyer/{buyerId}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public UserDTO getBuyerAgentForBuyer(@PathVariable("buyerId") Long buyerId);

	@GetMapping(value= {"/seller-agent/{sellerAgentId}/truck-driver"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public UserDTO getTruckDriverForSellerAgent(@PathVariable("sellerAgentId") Long sellerAgentId);

	@GetMapping(value= {"/truck-driver/{truckDriverId}/seller-agent"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public UserDTO getSellerAgentForTruckDriver(@PathVariable("truckDriverId") Long truckDriverId);
	
	@GetMapping(value= {"/truck-driver/{truckDriverId}/warehouse"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Map<String, Object> getWareHouseForTruckDriver(@PathVariable("truckDriverId") Long truckDriverId);
}
