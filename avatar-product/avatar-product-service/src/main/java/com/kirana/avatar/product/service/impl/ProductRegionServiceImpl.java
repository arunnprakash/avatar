/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import static com.kirana.avatar.product.model.ProductRegion_.PRODUCT_ID;
import static com.kirana.avatar.product.model.ProductRegion_.STATE;
import static com.kirana.avatar.product.model.ProductRegion_.DISTRICT;
import static com.kirana.avatar.product.model.ProductRegion_.TALUK;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.ProductAssetDTO;
import com.kirana.avatar.product.mapper.ProductAssetMapper;
import com.kirana.avatar.product.model.ProductAsset;
import com.kirana.avatar.product.repositories.ProductAssetRepository;
import com.kirana.avatar.product.resource.ProductAssetResource;
import com.kirana.avatar.product.specifications.ProductAssetSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class ProductRegionServiceImpl extends BaseServiceImpl<ProductAsset, ProductAssetDTO, ProductAssetMapper, ProductAssetRepository, ProductAssetSpecification> implements ProductAssetResource{
	
	private ProductAssetRepository productAssetRepository;
	private ProductAssetMapper productAssetMapper;
	private ProductAssetSpecification productAssetSpecification;
	public ProductRegionServiceImpl(ProductAssetRepository productAssetRepository, ProductAssetMapper productAssetMapper,ProductAssetSpecification productAssetSpecification) {
		super(productAssetRepository, productAssetMapper, productAssetSpecification);
		this.productAssetRepository = productAssetRepository;
		this.productAssetMapper = productAssetMapper;
		this.productAssetSpecification = productAssetSpecification;
	}
	@Override
	protected ProductAsset beforeSave(ProductAsset model) {
		return model;
	}
	@Override
	protected ProductAsset afterSave(ProductAsset model) {
		return model;
	}
	@Override
	protected ProductAsset beforeUpdate(ProductAssetDTO resource, ProductAsset model) {
		return model;
	}
	@Override
	protected ProductAsset afterUpdate(ProductAssetDTO resource, ProductAsset model) {
		return model;
	}
	@Override
	protected Specification<ProductAsset> getSpecification(FilterCriteria filter, Specification<ProductAsset> specification) {
		String itemName = filter.getFilterByItem();
		/*
		 * if (itemName.equalsIgnoreCase(PRODUCT_ID)) { Specification<ProductAsset> spec
		 * = null; for (String itemValue : filter.getFilterByItemValues()) { spec =
		 * (spec == null) ? productAssetSpecification.hasProductId(itemValue) :
		 * spec.or(productAssetSpecification.hasProductId(itemValue)); }
		 * log.debug("Adding specification {} {}", PRODUCT_ID, spec); specification =
		 * specification.and(spec); } else if (itemName.equalsIgnoreCase(ASSET_ID)) {
		 * Specification<ProductAsset> spec = null; for (String itemValue :
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
	public Collection<ProductAssetDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<ProductAssetDTO> getAllExceptDeleted() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PagingAndFilterResponse<ProductAssetDTO> getResourceByFilterAndPaging(
			@Valid PagingAndFilterRequest pagingAndFilterRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PagingAndFilterResponse<ProductAssetDTO> getResourceByFilterAndPagingExceptDeleted(
			@Valid PagingAndFilterRequest pagingAndFilterRequest) {
		// TODO Auto-generated method stub
		return null;
	}
}