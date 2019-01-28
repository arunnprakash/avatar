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

import com.kirana.avatar.authorization.model.District;
import com.kirana.avatar.authorization.model.State;
import com.kirana.avatar.authorization.model.Taluk;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.product.model.Product;
import com.kirana.avatar.product.model.ProductRegion;
import com.kirana.avatar.product.model.ProductRegion_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class ProductRegionSpecification extends BaseEntitySpecification<ProductRegion>{
	
	public Specification<ProductRegion> hasProductId(final Product productId) {
		return new Specification<ProductRegion>() {
			public Predicate toPredicate(Root<ProductRegion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(ProductRegion_.PRODUCT_ID), "%"+productId+"%");
			}
		};
	}
	
	public Specification<ProductRegion> hasStateId(final State stateId) {
		return new Specification<ProductRegion>() {
			public Predicate toPredicate(Root<ProductRegion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(ProductRegion_.STATE), "%"+stateId+"%");
			}
		};
	}
	
	public Specification<ProductRegion> hasDistrictId(final District districtId) {
		return new Specification<ProductRegion>() {
			public Predicate toPredicate(Root<ProductRegion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(ProductRegion_.DISTRICT), "%"+districtId+"%");
			}
		};
	}
	
	public Specification<ProductRegion> hasTalukId(final Taluk talukId) {
		return new Specification<ProductRegion>() {
			public Predicate toPredicate(Root<ProductRegion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(ProductRegion_.TALUK), "%"+talukId+"%");
			}
		};
	}

}
