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
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.resource.TalukResource;
import com.kirana.avatar.authorization.dto.TalukDTO;
import com.kirana.avatar.authorization.service.TalukService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __ArunPrakash__
 *
 */

@RestController
public class TalukController extends BaseController<TalukService, TalukDTO> implements TalukResource {

	@SuppressWarnings("unused")
	private TalukService talukService;

	public TalukController(TalukService talukService) {
		super(talukService);
		this.talukService = talukService;
	}
}
