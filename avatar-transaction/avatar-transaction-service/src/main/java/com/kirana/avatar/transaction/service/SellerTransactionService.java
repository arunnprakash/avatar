/*******************************************************************************
 *
 * Copyright (c) 2018 Granatech Limited
 *
 * All information contained herein is, and remains the property of Granatech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Granatech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from Granatech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.transaction.service;

import java.util.List;

import com.kirana.avatar.common.service.BaseService;
import com.kirana.avatar.transaction.dto.SellerOrder;
import com.kirana.avatar.transaction.dto.SellerTransactionDTO;

/**
 * @author __ArunPrakash__
 *
 */
public interface SellerTransactionService extends BaseService<SellerTransactionDTO>{

	public List<SellerOrder> getOrdersForSellerAgent(Long sellerAgentId, String orderCreatedDate);
	public List<SellerOrder> getOrdersForSellerTruckDriver(Long truckDriverId, String orderCreatedDate);
	public List<SellerOrder> getOrdersForWareHouse(Long wareHouseId, String orderCreatedDate);
	public List<SellerOrder> getOrdersFromSeller(Long sellerId, String orderCreatedDate);
}
