package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.AssetDTO;
import com.kirana.avatar.product.resource.AssetResource;
import com.kirana.avatar.product.service.AssetService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class AssetController extends BaseController<AssetService, AssetDTO> implements AssetResource {

	@SuppressWarnings("unused")
	private AssetService assertService;

	public AssetController(AssetService assertService) {
		super(assertService);
		this.assertService = assertService;
	}
}
