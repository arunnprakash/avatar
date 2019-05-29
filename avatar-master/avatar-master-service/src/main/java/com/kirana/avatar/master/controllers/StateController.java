/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.resource.StateResource;
import com.kirana.avatar.master.dto.StateDTO;
import com.kirana.avatar.master.service.StateService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __ArunPrakash__
 *
 */

@RestController
public class StateController extends BaseController<StateService, StateDTO> implements StateResource {

	@SuppressWarnings("unused")
	private StateService stateService;

	public StateController(StateService stateService) {
		super(stateService);
		this.stateService = stateService;
	}
}
