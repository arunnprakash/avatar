/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.SellerAgentCommissionDTO;
import com.kirana.avatar.master.resource.SellerAgentCommissionResource;
import com.kirana.avatar.master.service.SellerAgentCommissionService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SellerAgentCommissionController extends BaseController<SellerAgentCommissionService, SellerAgentCommissionDTO> implements SellerAgentCommissionResource {
	
	public SellerAgentCommissionController(SellerAgentCommissionService wareHouseService) {
		super(wareHouseService);
	}

}
