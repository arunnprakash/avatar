/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.ProductRegionDTO;
import com.kirana.avatar.product.mapper.ProductRegionMapper;
import com.kirana.avatar.product.model.ProductRegion;
import com.kirana.avatar.product.repositories.ProductRegionRepository;
import com.kirana.avatar.product.service.ProductRegionService;
import com.kirana.avatar.product.specifications.ProductRegionSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class ProductRegionServiceImpl extends BaseServiceImpl<ProductRegion, ProductRegionDTO, ProductRegionMapper, ProductRegionRepository, ProductRegionSpecification> implements ProductRegionService {
	
	private ProductRegionRepository productAssetRepository;
	private ProductRegionMapper productAssetMapper;
	private ProductRegionSpecification productAssetSpecification;
	public ProductRegionServiceImpl(ProductRegionRepository productAssetRepository, ProductRegionMapper productAssetMapper,ProductRegionSpecification productAssetSpecification) {
		super(productAssetRepository, productAssetMapper, productAssetSpecification);
		this.productAssetRepository = productAssetRepository;
		this.productAssetMapper = productAssetMapper;
		this.productAssetSpecification = productAssetSpecification;
	}
	@Override
	protected ProductRegion beforeSave(ProductRegionDTO productAssetDTO, ProductRegion model) {
		return model;
	}
	@Override
	protected ProductRegion afterSave(ProductRegionDTO productAssetDTO, ProductRegion model) {
		return model;
	}
	@Override
	protected ProductRegion beforeUpdate(ProductRegionDTO resource, ProductRegion model) {
		return model;
	}
	@Override
	protected ProductRegion afterUpdate(ProductRegionDTO resource, ProductRegion model) {
		return model;
	}
	@Override
	protected Specification<ProductRegion> getSpecification(FilterCriteria filter, Specification<ProductRegion> specification) {
		String itemName = filter.getFilterByItem();
		/*
		 * if (itemName.equalsIgnoreCase(PRODUCT_ID)) { Specification<ProductRegion> spec
		 * = null; for (String itemValue : filter.getFilterByItemValues()) { spec =
		 * (spec == null) ? productAssetSpecification.hasProductId(itemValue) :
		 * spec.or(productAssetSpecification.hasProductId(itemValue)); }
		 * log.debug("Adding specification {} {}", PRODUCT_ID, spec); specification =
		 * specification.and(spec); } else if (itemName.equalsIgnoreCase(ASSET_ID)) {
		 * Specification<ProductRegion> spec = null; for (String itemValue :
		 * filter.getFilterByItemValues()) { spec = (spec == null) ?
		 * productAssetSpecification.hasAssetId(itemValue) :
		 * spec.or(productAssetSpecification.hasAssetId(itemValue)); }
		 * log.debug("Adding specification {} {}", ASSET_ID, spec); specification =
		 * specification.and(spec); } else { throw
		 * ApiException.implementationNotFound(); }
		 */
		return specification;
	}
	@Override
	protected ProductRegionDTO afterLoad(ProductRegionDTO resource, ProductRegion model) {
		return resource;
	}
}
