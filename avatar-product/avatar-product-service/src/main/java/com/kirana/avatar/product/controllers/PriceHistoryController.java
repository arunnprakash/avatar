/**
 * 
 */
package com.kirana.avatar.product.controllers;

import java.time.ZonedDateTime;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.product.dto.PriceHistoryDTO;
import com.kirana.avatar.product.resource.PriceHistoryResource;
import com.kirana.avatar.product.service.PriceHistoryService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class PriceHistoryController extends BaseController<PriceHistoryService, PriceHistoryDTO> implements PriceHistoryResource {

		
	private PriceHistoryService priceHistoryService;

	public PriceHistoryController(PriceHistoryService priceHistoryService) {
		super(priceHistoryService);
		this.priceHistoryService = priceHistoryService;
	}

	@Override
	public PagingAndFilterResponse<BaseDTO> getProductsForUser(PagingAndFilterRequest pagingAndFilterRequest, Long userId, Long talukId, Long districtId, Long stateId) {
		PagingAndFilterResponse<BaseDTO> prices = priceHistoryService.getProductsForUser(pagingAndFilterRequest, userId, talukId, districtId, stateId);
		return prices;
	}

	@Override
	public PriceHistoryDTO getPriceForProduct(Long productId, Long qualityId, String pricePublishedDate) {
		return priceHistoryService.getPriceForProduct(productId, qualityId, pricePublishedDate);
	}

}
