/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.VillageDTO;
import com.kirana.avatar.master.resource.VillageResource;
import com.kirana.avatar.master.service.VillageService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class VillageController extends BaseController<VillageService, VillageDTO> implements VillageResource{
	
	public VillageController(VillageService villageService) {
		super(villageService);
	}

}
