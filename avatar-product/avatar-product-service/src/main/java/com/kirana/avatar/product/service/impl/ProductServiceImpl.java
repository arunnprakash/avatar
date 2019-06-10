/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.kirana.avatar.product.model.Product_.PRODUCT_CODE;

import com.kirana.avatar.product.dto.AssetDTO;
import com.kirana.avatar.product.model.Asset;
import com.kirana.avatar.product.model.AssetType;
import com.kirana.avatar.product.model.ProductAsset;
import com.kirana.avatar.product.repositories.AssetRepository;
import com.kirana.avatar.product.repositories.AssetTypeRepository;
import com.kirana.avatar.product.repositories.ProductAssetRepository;
import com.kirana.avatar.product.mapper.AssetMapper;
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
	private AssetRepository assetRepository;
	private ProductAssetRepository productAssetRepository;
	private AssetTypeRepository assetTypeRepository;
	private AssetMapper assetMapper;
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,ProductSpecification productSpecification,
			AssetRepository assetRepository, ProductAssetRepository productAssetRepository, AssetMapper assetMapper,
			AssetTypeRepository assetTypeRepository) {
		super(productRepository, productMapper, productSpecification);
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productSpecification = productSpecification;
		this.assetRepository = assetRepository;
		this.productAssetRepository = productAssetRepository;
		this.assetTypeRepository = assetTypeRepository;
		this.assetMapper = assetMapper;
	}
	@Override
	protected Product beforeSave(ProductDTO resource, Product model) {
		model.setAssets(null);
		return model;
	}
	@Override
	protected Product afterSave(ProductDTO resource, Product model) {
		if (null != resource.getAssets() && !resource.getAssets().isEmpty()) {
			for (AssetDTO assetDTO : resource.getAssets()) {
				Asset asset = assetMapper.toModel(assetDTO);
				AssetType assetType = assetTypeRepository.getOne(asset.getAssetType().getId());
				asset.setAssetType(assetType);
				asset = assetRepository.save(asset);
				ProductAsset productAsset = new ProductAsset();
				productAsset.setProduct(model);
				productAsset.setAsset(asset);
				productAssetRepository.save(productAsset);
			}
		}
		return productRepository.findById(model.getId()).get();
	}
	@Override
	protected Product beforeUpdate(ProductDTO resource, Product model) {
		model.setAssets(null);
		return model;
	}
	@Override
	protected Product afterUpdate(ProductDTO resource, Product model) {
		if (null != resource.getAssets() && !resource.getAssets().isEmpty()) {
			for (AssetDTO assetDTO : resource.getAssets()) {
				Asset asset = null;
				if (null != assetDTO.getId()) {
					asset = assetRepository.getOne(assetDTO.getId());
				} else {
					asset = assetMapper.toModel(assetDTO);
				}
				AssetType assetType = assetTypeRepository.getOne(asset.getAssetType().getId());
				asset.setAssetType(assetType);
				asset = assetRepository.save(asset);
				ProductAsset productAsset = new ProductAsset();
				productAsset.setProduct(model);
				productAsset.setAsset(asset);
				productAssetRepository.save(productAsset);
			}
		}
		model = productRepository.findById(model.getId()).get();
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
	@Override
	protected ProductDTO afterLoad(ProductDTO resource, Product model) {
		return resource;
	}

}
