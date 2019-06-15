package com.kirana.avatar.offline.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.offline.dto.IncomingSmsDTO;
import com.kirana.avatar.offline.resource.IncomingSmsResource;
import com.kirana.avatar.offline.service.IncomingSmsService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class IncomingSmsController extends BaseController<IncomingSmsService, IncomingSmsDTO> implements IncomingSmsResource {

	public IncomingSmsController(IncomingSmsService incomingSmsService) {
		super(incomingSmsService);
	}

	@Override
	public boolean receiveSms(IncomingSmsDTO resource) {
		super.save(resource);
		return true;
	}
}
