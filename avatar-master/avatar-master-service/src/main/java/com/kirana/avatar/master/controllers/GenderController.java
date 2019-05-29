package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.GenderDTO;
import com.kirana.avatar.master.resource.GenderResource;
import com.kirana.avatar.master.service.GenderService;
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
