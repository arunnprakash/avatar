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
import com.kirana.avatar.product.model.Product_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class ProductSpecification extends BaseEntitySpecification<Product>{
	
	public Specification<Product> hasProductCode(final String ProductCode) {
		return new Specification<Product>() {
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Product_.PRODUCT_CODE), "%"+ProductCode+"%");
			}
		};
	}

}
