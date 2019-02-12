package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.AssetTypeDTO;
import com.kirana.avatar.product.resource.AssetTypeResource;
import com.kirana.avatar.product.service.AssetTypeService;

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
