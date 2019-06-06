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
import com.kirana.avatar.product.model.MarketPrice;
import com.kirana.avatar.product.model.MarketPrice_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class MarketPriceSpecification extends BaseEntitySpecification<MarketPrice>{
	
	public Specification<MarketPrice> hasPrice(final String price) {
		return new Specification<MarketPrice>() {
			public Predicate toPredicate(Root<MarketPrice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(MarketPrice_.PRICE), "%"+price+"%");
			}
		};
	}

}
