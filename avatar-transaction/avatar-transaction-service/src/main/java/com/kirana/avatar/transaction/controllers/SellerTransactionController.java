package com.kirana.avatar.transaction.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.transaction.resource.SellerTransactionResource;
import com.kirana.avatar.transaction.service.SellerTransactionService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SellerTransactionController extends BaseController<SellerTransactionService, SellerTransactionDTO> implements SellerTransactionResource {

	@SuppressWarnings("unused")
	private SellerTransactionService assertService;

	public SellerTransactionController(SellerTransactionService assertService) {
		super(assertService);
		this.assertService = assertService;
	}
}
