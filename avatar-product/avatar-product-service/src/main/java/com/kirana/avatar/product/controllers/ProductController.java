/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.ProductDTO;
import com.kirana.avatar.product.resource.ProductResource;
import com.kirana.avatar.product.service.ProductService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class ProductController extends BaseController<ProductService, ProductDTO> implements ProductResource{

		
	private ProductService productService;

	public ProductController(ProductService productService) {
		super(productService);
		this.productService	= productService;
	}

}
