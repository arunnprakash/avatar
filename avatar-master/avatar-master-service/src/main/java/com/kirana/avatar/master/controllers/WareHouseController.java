/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.WareHouseDTO;
import com.kirana.avatar.master.resource.WareHouseResource;
import com.kirana.avatar.master.service.WareHouseService;
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
