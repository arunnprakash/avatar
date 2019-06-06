/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.MarketPriceDTO;
import com.kirana.avatar.product.resource.MarketPriceResource;
import com.kirana.avatar.product.service.MarketPriceService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class MarketPriceController extends BaseController<MarketPriceService, MarketPriceDTO> implements MarketPriceResource {

		
	private MarketPriceService marketPriceService;

	public MarketPriceController(MarketPriceService marketPriceService) {
		super(marketPriceService);
		this.marketPriceService = marketPriceService;
	}
}
