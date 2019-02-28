/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.TruckDriverWareHouseMappingDTO;
import com.kirana.avatar.authorization.resource.TruckDriverWareHouseMappingResource;
import com.kirana.avatar.authorization.service.TruckDriverWareHouseMappingService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class TruckDriverWareHouseMappingController extends BaseController<TruckDriverWareHouseMappingService, TruckDriverWareHouseMappingDTO> implements TruckDriverWareHouseMappingResource {
	
	public TruckDriverWareHouseMappingController(TruckDriverWareHouseMappingService truckDriverWareHouseMappingService) {
		super(truckDriverWareHouseMappingService);
	}

}
