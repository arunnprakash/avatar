/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.SellerAgentWareHouseMappingDTO;
import com.kirana.avatar.authorization.resource.SellerAgentWareHouseMappingResource;
import com.kirana.avatar.authorization.service.SellerAgentWareHouseMappingService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SellerAgentWareHouseMappingController extends BaseController<SellerAgentWareHouseMappingService, SellerAgentWareHouseMappingDTO> implements SellerAgentWareHouseMappingResource {
	
	public SellerAgentWareHouseMappingController(SellerAgentWareHouseMappingService sellerAgentWareHouseMappingService) {
		super(sellerAgentWareHouseMappingService);
	}

}
