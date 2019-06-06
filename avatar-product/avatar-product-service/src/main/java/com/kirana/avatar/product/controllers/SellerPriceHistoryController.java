/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.resource.SellerPriceHistoryResource;
import com.kirana.avatar.product.service.SellerPriceHistoryService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class SellerPriceHistoryController extends BaseController<SellerPriceHistoryService, SellerPriceHistoryDTO> implements SellerPriceHistoryResource {

		
	private SellerPriceHistoryService sellerPriceHistoryService;

	public SellerPriceHistoryController(SellerPriceHistoryService sellerPriceHistoryService) {
		super(sellerPriceHistoryService);
		this.sellerPriceHistoryService = sellerPriceHistoryService;
	}

	@Override
	public PagingAndFilterResponse<BaseDTO> getProductsForUser(PagingAndFilterRequest pagingAndFilterRequest, Long userId, Long talukId, Long districtId, Long stateId) {
		return sellerPriceHistoryService.getProductsForUser(pagingAndFilterRequest, userId, talukId, districtId, stateId);
	}

	@Override
	public SellerPriceHistoryDTO getPriceForProduct(Long productId, Long qualityId, String pricePublishedDate) {
		return sellerPriceHistoryService.getPriceForProduct(productId, qualityId, pricePublishedDate);
	}

}
