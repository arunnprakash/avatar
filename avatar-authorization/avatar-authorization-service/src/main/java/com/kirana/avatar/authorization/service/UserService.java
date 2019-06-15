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
package com.kirana.avatar.authorization.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.common.service.BaseService;

/**
 * @author __ArunPrakash__
 *
 */
public interface UserService extends BaseService<UserDTO>{

	UserDTO findByUserNameOrMobileNumber(String userNameOrMobileNumber);

	public Map<String, Long> sellersDailyGrowthRate(@PathVariable("depth") Integer depth);
	
	public Map<String, Long> sellersMonthlyGrowthRate(@PathVariable("depth") Integer depth);
	
	public Map<String, Long> sellersYearlyGrowthRate(@PathVariable("depth") Integer depth);

	public Map<String, Long> buyersDailyGrowthRate(@PathVariable("depth") Integer depth);
	
	public Map<String, Long> buyersMonthlyGrowthRate(@PathVariable("depth") Integer depth);
	
	public Map<String, Long> buyersYearlyGrowthRate(@PathVariable("depth") Integer depth);

	public UserDTO getSellerAgentForSeller(Long sellerId);

	public UserDTO getBuyerAgentForBuyer(Long buyerId);

	public UserDTO getTruckDriverForSellerAgent(Long sellerAgentId);
	
	public UserDTO getSellerAgentForTruckDriver(Long truckDriverId);

	public Map<String, Object> getWareHouseForTruckDriver(Long truckDriverId);

	List<UserDTO> getSellerAgentByWareHouse(Long wareHouseId);

	List<UserDTO> getSellersBySellerAgent(Long sellerAgentId);
}
