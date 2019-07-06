package com.kirana.avatar.finance.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.finance.dto.BankAccountDTO;
import com.kirana.avatar.finance.resource.BankAccountResource;
import com.kirana.avatar.finance.service.BankAccountService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class BankAccountController extends BaseController<BankAccountService, BankAccountDTO> implements BankAccountResource {

	private BankAccountService bankAccountService;

	public BankAccountController(BankAccountService bankAccountService) {
		super(bankAccountService);
		this.bankAccountService = bankAccountService;
	}

}
