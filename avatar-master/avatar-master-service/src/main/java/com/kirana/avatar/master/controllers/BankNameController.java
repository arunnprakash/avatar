/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.BankNameDTO;
import com.kirana.avatar.master.resource.BankNameResource;
import com.kirana.avatar.master.service.BankNameService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class BankNameController extends BaseController<BankNameService, BankNameDTO> implements BankNameResource {
	
	public BankNameController(BankNameService bankNameService) {
		super(bankNameService);
	}

}
