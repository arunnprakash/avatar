package com.kirana.avatar.authorization.service.impl;

import static com.kirana.avatar.authorization.model.Asset_.ASSET_VALUE;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.kirana.avatar.authorization.dto.AssetDTO;
import com.kirana.avatar.authorization.mapper.AssetMapper;
import com.kirana.avatar.authorization.model.Asset;
import com.kirana.avatar.authorization.model.AssetType;
import com.kirana.avatar.authorization.repositories.AssetRepository;
import com.kirana.avatar.authorization.service.AssetService;
import com.kirana.avatar.authorization.specifications.AssetSpecification;
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
	protected Asset beforeSave(Asset model) {
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

}
