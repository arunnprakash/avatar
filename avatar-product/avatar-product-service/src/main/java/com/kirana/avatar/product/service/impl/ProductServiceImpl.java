/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.kirana.avatar.product.model.Product_.PRODUCT_CODE;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.ProductDTO;
import com.kirana.avatar.product.mapper.ProductMapper;
import com.kirana.avatar.product.model.Product;
import com.kirana.avatar.product.repositories.ProductRepository;
import com.kirana.avatar.product.service.ProductService;
import com.kirana.avatar.product.specifications.ProductSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductDTO, ProductMapper, ProductRepository, ProductSpecification> implements ProductService{
	
	private ProductRepository productRepository;
	private ProductMapper productMapper;
	private ProductSpecification productSpecification;
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,ProductSpecification productSpecification) {
		super(productRepository, productMapper, productSpecification);
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productSpecification = productSpecification;
	}
	@Override
	protected Product beforeSave(Product model) {
		return model;
	}
	@Override
	protected Product afterSave(ProductDTO productDTO, Product model) {
		return model;
	}
	@Override
	protected Product beforeUpdate(ProductDTO resource, Product model) {
		return model;
	}
	@Override
	protected Product afterUpdate(ProductDTO resource, Product model) {
		return model;
	}
	@Override
	protected Specification<Product> getSpecification(FilterCriteria filter, Specification<Product> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(PRODUCT_CODE)) {
			Specification<Product> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? productSpecification.hasProductCode(itemValue) : spec.or(productSpecification.hasProductCode(itemValue));
			}
			log.debug("Adding specification {} {}", PRODUCT_CODE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
