/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.ProductAssetDTO;
import com.kirana.avatar.product.resource.ProductAssetResource;
import com.kirana.avatar.product.service.ProductAssetService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class ProductAssetController extends BaseController<ProductAssetService, ProductAssetDTO> implements ProductAssetResource{

		
	private ProductAssetService productAssetService;

	public ProductAssetController(ProductAssetService productAssetService) {
		super(productAssetService);
		this.productAssetService = productAssetService;
	}

}
