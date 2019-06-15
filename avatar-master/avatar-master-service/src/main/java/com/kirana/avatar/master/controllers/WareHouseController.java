/**
 * 
 */
package com.kirana.avatar.master.controllers;

import java.util.List;

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
	
	private WareHouseService wareHouseService;
	public WareHouseController(WareHouseService wareHouseService) {
		super(wareHouseService);
		this.wareHouseService = wareHouseService;
	}

	@Override
	public List<WareHouseDTO> getWareHousesByMarket(Long marketId) {
		return wareHouseService.getWareHousesByMarket(marketId);
	}

}
