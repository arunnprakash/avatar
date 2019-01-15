/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.VillageDTO;
import com.kirana.avatar.authorization.resource.VillageResource;
import com.kirana.avatar.authorization.service.VillageService;
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
