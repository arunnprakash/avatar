package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.AssetDTO;
import com.kirana.avatar.authorization.resource.AssetResource;
import com.kirana.avatar.authorization.service.AssetService;
import com.kirana.avatar.common.controllers.BaseController;

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
