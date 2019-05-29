package com.kirana.avatar.master.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.master.model.AssetType;
import com.kirana.avatar.master.model.AssetType_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class AssetTypeSpecification extends BaseEntitySpecification<AssetType>{
	
	public Specification<AssetType> hasAssetTypeName(final String assetTypeName) {
		return new Specification<AssetType>() {
			public Predicate toPredicate(Root<AssetType> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(AssetType_.ASSET_TYPE_NAME), "%"+assetTypeName+"%");
			}
		};
	}

}
