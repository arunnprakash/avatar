/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.MarketDTO;
import com.kirana.avatar.master.resource.MarketResource;
import com.kirana.avatar.master.service.MarketService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class MarketController extends BaseController<MarketService, MarketDTO> implements MarketResource {
	
	public MarketController(MarketService marketService) {
		super(marketService);
	}

}
