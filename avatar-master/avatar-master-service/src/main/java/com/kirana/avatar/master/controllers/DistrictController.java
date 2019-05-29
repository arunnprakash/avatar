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

import com.kirana.avatar.master.resource.DistrictResource;
import com.kirana.avatar.master.dto.DistrictDTO;
import com.kirana.avatar.master.service.DistrictService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __ArunPrakash__
 *
 */

@RestController
public class DistrictController extends BaseController<DistrictService, DistrictDTO> implements DistrictResource {

	@SuppressWarnings("unused")
	private DistrictService districtService;

	public DistrictController(DistrictService districtService) {
		super(districtService);
		this.districtService = districtService;
	}
}
