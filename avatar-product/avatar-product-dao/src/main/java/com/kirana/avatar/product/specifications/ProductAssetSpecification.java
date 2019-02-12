/**
 * 
 */
package com.kirana.avatar.product.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.product.model.Asset;
import com.kirana.avatar.product.model.Product;
import com.kirana.avatar.product.model.ProductAsset;
import com.kirana.avatar.product.model.ProductAsset_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class ProductAssetSpecification extends BaseEntitySpecification<ProductAsset>{
	
	public Specification<ProductAsset> hasProductId(final Product productId) {
		return new Specification<ProductAsset>() {
			public Predicate toPredicate(Root<ProductAsset> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(ProductAsset_.PRODUCT_ID), "%"+productId+"%");
			}
		};
	}
	
	public Specification<ProductAsset> hasAssetId(final Asset AssetId) {
		return new Specification<ProductAsset>() {
			public Predicate toPredicate(Root<ProductAsset> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(ProductAsset_.ASSET_ID), "%"+AssetId+"%");
			}
		};
	}

}
