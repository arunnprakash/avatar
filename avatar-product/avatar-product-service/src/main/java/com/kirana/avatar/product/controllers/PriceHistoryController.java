/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.PriceHistoryDTO;
import com.kirana.avatar.product.resource.PriceHistoryResource;
import com.kirana.avatar.product.service.PriceHistoryService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class PriceHistoryController extends BaseController<PriceHistoryService, PriceHistoryDTO> implements PriceHistoryResource{

		
	private PriceHistoryService priceHistoryService;

	public PriceHistoryController(PriceHistoryService priceHistoryService) {
		super(priceHistoryService);
		this.priceHistoryService = priceHistoryService;
	}

}
