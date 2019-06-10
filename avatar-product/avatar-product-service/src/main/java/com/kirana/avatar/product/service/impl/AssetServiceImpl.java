package com.kirana.avatar.product.service.impl;

import static com.kirana.avatar.product.model.Asset_.ASSET_VALUE;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.kirana.avatar.product.dto.AssetDTO;
import com.kirana.avatar.product.mapper.AssetMapper;
import com.kirana.avatar.product.model.Asset;
import com.kirana.avatar.product.model.AssetType;
import com.kirana.avatar.product.repositories.AssetRepository;
import com.kirana.avatar.product.service.AssetService;
import com.kirana.avatar.product.specifications.AssetSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class AssetServiceImpl extends BaseServiceImpl<Asset, AssetDTO, AssetMapper, AssetRepository, AssetSpecification> implements AssetService {

	private AssetRepository assetRepository;
	private AssetMapper assetMapper;
	private AssetSpecification assetSpecification;
	public AssetServiceImpl(AssetRepository assetRepository, AssetMapper assetMapper, AssetSpecification assetSpecification) {
		super(assetRepository, assetMapper, assetSpecification);
		this.assetRepository = assetRepository;
		this.assetMapper = assetMapper;
		this.assetSpecification = assetSpecification;
	}

	@Override
	protected Asset beforeSave(AssetDTO assetDTO, Asset model) {
		return model;
	}

	@Override
	protected Asset beforeUpdate(AssetDTO assetDTO, Asset model) {
		return model;
	}

	@Override
	protected Asset afterSave(AssetDTO assetDTO, Asset model) {
		return model;
	}

	@Override
	protected Asset afterUpdate(AssetDTO assetDTO, Asset model) {
		return model;
	}

	@Override
	protected Specification<Asset> getSpecification(FilterCriteria filter, Specification<Asset> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(ASSET_VALUE)) {
			Specification<Asset> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? assetSpecification.hasAssetValue(itemValue) : spec.or(assetSpecification.hasAssetValue(itemValue));
			}
			log.debug("Adding specification {} {}", ASSET_VALUE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

	@Override
	protected AssetDTO afterLoad(AssetDTO resource, Asset model) {
		return resource;
	}

}
