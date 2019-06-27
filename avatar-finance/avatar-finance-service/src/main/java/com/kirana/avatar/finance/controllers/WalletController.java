package com.kirana.avatar.finance.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.finance.dto.WalletDTO;
import com.kirana.avatar.finance.resource.WalletResource;
import com.kirana.avatar.finance.service.WalletService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class WalletController extends BaseController<WalletService, WalletDTO> implements WalletResource {

	private WalletService walletService;

	public WalletController(WalletService walletService) {
		super(walletService);
		this.walletService = walletService;
	}

}
