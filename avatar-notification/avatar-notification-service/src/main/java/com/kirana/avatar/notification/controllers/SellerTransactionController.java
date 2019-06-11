package com.kirana.avatar.transaction.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.transaction.dto.SellerOrder;
import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.transaction.resource.SellerTransactionResource;
import com.kirana.avatar.transaction.service.SellerTransactionService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SellerTransactionController extends BaseController<SellerTransactionService, SellerTransactionDTO> implements SellerTransactionResource {

	private SellerTransactionService sellerTransactionService;

	public SellerTransactionController(SellerTransactionService sellerTransactionService) {
		super(sellerTransactionService);
		this.sellerTransactionService = sellerTransactionService;
	}

	@Override
	public List<SellerOrder> getOrdersForSellerAgent(Long sellerAgentId, String orderCreatedDate) {
		return sellerTransactionService.getOrdersForSellerAgent(sellerAgentId, orderCreatedDate);
	}

	@Override
	public List<SellerOrder> getOrdersForSellerTruckDriver(Long truckDriverId, String orderCreatedDate) {
		return sellerTransactionService.getOrdersForSellerTruckDriver(truckDriverId, orderCreatedDate);
	}

	@Override
	public List<SellerOrder> getOrdersForWareHouse(Long wareHouseId, String orderCreatedDate) {
		return sellerTransactionService.getOrdersForWareHouse(wareHouseId, orderCreatedDate);
	}

	@Override
	public List<SellerOrder> getOrdersFromSeller(Long sellerId, String orderCreatedDate) {
		return sellerTransactionService.getOrdersFromSeller(sellerId, orderCreatedDate);
	}
}
