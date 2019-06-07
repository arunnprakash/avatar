/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.SellerMerchantCommissionDTO;
import com.kirana.avatar.master.resource.SellerMerchantCommissionResource;
import com.kirana.avatar.master.service.SellerMerchantCommissionService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SellerMerchantCommissionController extends BaseController<SellerMerchantCommissionService, SellerMerchantCommissionDTO> implements SellerMerchantCommissionResource {
	
	public SellerMerchantCommissionController(SellerMerchantCommissionService sellerMerchantCommissionService) {
		super(sellerMerchantCommissionService);
	}

}
