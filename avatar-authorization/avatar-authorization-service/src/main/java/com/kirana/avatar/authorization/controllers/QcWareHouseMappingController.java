/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.QcWareHouseMappingDTO;
import com.kirana.avatar.authorization.resource.QcWareHouseMappingResource;
import com.kirana.avatar.authorization.service.QcWareHouseMappingService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class QcWareHouseMappingController extends BaseController<QcWareHouseMappingService, QcWareHouseMappingDTO> implements QcWareHouseMappingResource {
	
	private QcWareHouseMappingService qcWareHouseMappingService;
	
	public QcWareHouseMappingController(QcWareHouseMappingService qcWareHouseMappingService) {
		super(qcWareHouseMappingService);
		this.qcWareHouseMappingService = qcWareHouseMappingService;
	}

	@Override
	public QcWareHouseMappingDTO findByQcId(Long qcId) {
		return qcWareHouseMappingService.findByQcId(qcId);
	}

}
