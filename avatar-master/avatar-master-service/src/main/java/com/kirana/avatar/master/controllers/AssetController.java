package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.AssetDTO;
import com.kirana.avatar.master.resource.AssetResource;
import com.kirana.avatar.master.service.AssetService;
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
