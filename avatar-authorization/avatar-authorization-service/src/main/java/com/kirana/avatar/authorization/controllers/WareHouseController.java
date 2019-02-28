/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.WareHouseDTO;
import com.kirana.avatar.authorization.resource.WareHouseResource;
import com.kirana.avatar.authorization.service.WareHouseService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class WareHouseController extends BaseController<WareHouseService, WareHouseDTO> implements WareHouseResource {
	
	public WareHouseController(WareHouseService wareHouseService) {
		super(wareHouseService);
	}

}
