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
import com.kirana.avatar.product.model.PriceHistory;
import com.kirana.avatar.product.model.PriceHistory_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class PriceHistorySpecification extends BaseEntitySpecification<PriceHistory>{
	
	public Specification<PriceHistory> hasPrice(final String price) {
		return new Specification<PriceHistory>() {
			public Predicate toPredicate(Root<PriceHistory> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(PriceHistory_.PRICE), "%"+price+"%");
			}
		};
	}

}
