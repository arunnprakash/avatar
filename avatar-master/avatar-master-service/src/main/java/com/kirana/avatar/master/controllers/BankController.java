/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.BankDTO;
import com.kirana.avatar.master.resource.BankResource;
import com.kirana.avatar.master.service.BankService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class BankController extends BaseController<BankService, BankDTO> implements BankResource {
	
	public BankController(BankService bankService) {
		super(bankService);
	}

}
