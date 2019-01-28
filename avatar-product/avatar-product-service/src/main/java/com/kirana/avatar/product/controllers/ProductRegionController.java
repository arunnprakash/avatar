/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.ProductRegionDTO;
import com.kirana.avatar.product.resource.ProductRegionResource;
import com.kirana.avatar.product.service.ProductRegionService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class ProductRegionController extends BaseController<ProductRegionService, ProductRegionDTO> implements ProductRegionResource{
	

		
	private ProductRegionService productRegionService;

	public ProductRegionController(ProductRegionService productRegionService) {
		super(productRegionService);
		this.productRegionService = productRegionService;
	}

}
