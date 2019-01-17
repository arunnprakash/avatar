package com.kirana.avatar.authorization.service.impl;

import static com.kirana.avatar.authorization.model.AssetType_.ASSET_TYPE_NAME;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.kirana.avatar.authorization.dto.AssetTypeDTO;
import com.kirana.avatar.authorization.dto.TalukDTO;
import com.kirana.avatar.authorization.mapper.AssetTypeMapper;
import com.kirana.avatar.authorization.model.AssetType;
import com.kirana.avatar.authorization.model.Taluk;
import com.kirana.avatar.authorization.repositories.AssetTypeRepository;
import com.kirana.avatar.authorization.service.AssetTypeService;
import com.kirana.avatar.authorization.specifications.AssetTypeSpecification;
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
public class AssetTypeServiceImpl extends BaseServiceImpl<AssetType, AssetTypeDTO, AssetTypeMapper, AssetTypeRepository, AssetTypeSpecification> implements AssetTypeService {

	private AssetTypeRepository assetTypeRepository;
	private AssetTypeMapper assetTypeMapper;
	private AssetTypeSpecification assetTypeSpecification;
	public AssetTypeServiceImpl(AssetTypeRepository assetTypeRepository, AssetTypeMapper assetTypeMapper, AssetTypeSpecification assetTypeSpecification) {
		super(assetTypeRepository, assetTypeMapper, assetTypeSpecification);
		this.assetTypeRepository = assetTypeRepository;
		this.assetTypeMapper = assetTypeMapper;
		this.assetTypeSpecification = assetTypeSpecification;
	}
	
	@Override
	protected AssetType onSave(AssetType model) {
		return model;
	}

	@Override
	protected AssetType onUpdate(AssetTypeDTO assetTypeDTO, AssetType model) {
		return model;
	}

	@Override
	protected Specification<AssetType> getSpecification(FilterCriteria filter, Specification<AssetType> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(ASSET_TYPE_NAME)) {
			Specification<AssetType> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? assetTypeSpecification.hasAssetTypeName(itemValue) : spec.or(assetTypeSpecification.hasAssetTypeName(itemValue));
			}
			log.debug("Adding specification {} {}", ASSET_TYPE_NAME, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
