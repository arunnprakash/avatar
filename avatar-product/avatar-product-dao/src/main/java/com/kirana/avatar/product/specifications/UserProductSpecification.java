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
import com.kirana.avatar.product.model.Product;
import com.kirana.avatar.product.model.UserProduct;
import com.kirana.avatar.product.model.UserProduct_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class UserProductSpecification extends BaseEntitySpecification<UserProduct>{
	
	public Specification<UserProduct> hasProductId(final Product productId) {
		return new Specification<UserProduct>() {
			public Predicate toPredicate(Root<UserProduct> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(UserProduct_.PRODUCT), "%"+productId+"%");
			}
		};
	}
	
	public Specification<UserProduct> hasUserId(final Long userId) {
		return new Specification<UserProduct>() {
			public Predicate toPredicate(Root<UserProduct> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(UserProduct_.USER), userId);
			}
		};
	}

}
