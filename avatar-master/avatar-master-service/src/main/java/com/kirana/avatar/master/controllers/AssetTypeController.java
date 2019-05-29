package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.AssetTypeDTO;
import com.kirana.avatar.master.resource.AssetTypeResource;
import com.kirana.avatar.master.service.AssetTypeService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class AssetTypeController extends BaseController<AssetTypeService, AssetTypeDTO> implements AssetTypeResource {

	@SuppressWarnings("unused")
	private AssetTypeService assertTypeService;

	public AssetTypeController(AssetTypeService assertTypeService) {
		super(assertTypeService);
		this.assertTypeService = assertTypeService;
	}
}
