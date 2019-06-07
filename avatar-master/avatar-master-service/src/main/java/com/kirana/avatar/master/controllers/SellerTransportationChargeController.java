/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.SellerTransportationChargeDTO;
import com.kirana.avatar.master.resource.SellerTransportationChargeResource;
import com.kirana.avatar.master.service.SellerTransportationChargeService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SellerTransportationChargeController extends BaseController<SellerTransportationChargeService, SellerTransportationChargeDTO> implements SellerTransportationChargeResource {
	
	public SellerTransportationChargeController(SellerTransportationChargeService sellerTransportationChargeService) {
		super(sellerTransportationChargeService);
	}

}
