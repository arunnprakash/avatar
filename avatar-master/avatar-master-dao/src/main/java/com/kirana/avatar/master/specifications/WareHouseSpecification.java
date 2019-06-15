/**
 * 
 */
package com.kirana.avatar.master.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.master.model.WareHouse;
import com.kirana.avatar.master.model.WareHouse_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class WareHouseSpecification extends BaseEntitySpecification<WareHouse> {

	public Specification<WareHouse> hasMarket(Long marketId) {
		return new Specification<WareHouse>() {
			public Predicate toPredicate(Root<WareHouse> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(WareHouse_.MARKET).get(BaseEntity_.ID), marketId);
			}
		};
	}

}
