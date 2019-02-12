package com.kirana.avatar.product.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.product.model.Asset;
import com.kirana.avatar.product.model.Asset_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class AssetSpecification extends BaseEntitySpecification<Asset>{
	
	public Specification<Asset> hasAssetValue(final String assetValue) {
		return new Specification<Asset>() {
			public Predicate toPredicate(Root<Asset> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Asset_.ASSET_VALUE), "%"+assetValue+"%");
			}
		};
	}

}
