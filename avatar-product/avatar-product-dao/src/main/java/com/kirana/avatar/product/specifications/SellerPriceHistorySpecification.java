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
import com.kirana.avatar.product.model.SellerPriceHistory;
import com.kirana.avatar.product.model.SellerPriceHistory_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class SellerPriceHistorySpecification extends BaseEntitySpecification<SellerPriceHistory>{
	
	public Specification<SellerPriceHistory> hasPrice(final String price) {
		return new Specification<SellerPriceHistory>() {
			public Predicate toPredicate(Root<SellerPriceHistory> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(SellerPriceHistory_.PRICE), "%"+price+"%");
			}
		};
	}

}
