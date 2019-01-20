package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.GenderDTO;
import com.kirana.avatar.authorization.resource.GenderResource;
import com.kirana.avatar.authorization.service.GenderService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class GenderController extends BaseController<GenderService, GenderDTO> implements GenderResource {

	@SuppressWarnings("unused")
	private GenderService genderService;

	public GenderController(GenderService genderService) {
		super(genderService);
		this.genderService = genderService;
	}
}
