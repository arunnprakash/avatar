/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.QualityDTO;
import com.kirana.avatar.product.resource.QualityResource;
import com.kirana.avatar.product.service.QualityService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class QualityController extends BaseController<QualityService, QualityDTO> implements QualityResource{

		
	private QualityService qualityService;

	public QualityController(QualityService qualityService) {
		super(qualityService);
		this.qualityService	= qualityService;
	}

}
