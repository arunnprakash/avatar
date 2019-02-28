/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.authorization.model.BuyerAgentMapping;
import com.kirana.avatar.authorization.model.BuyerAgentMapping_;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __ArunPrakash__
 *
 */
@SuppressWarnings("serial")
@Component
public class BuyerAgentMappingSpecification extends BaseEntitySpecification<BuyerAgentMapping> {

	public Specification<BuyerAgentMapping> hasBuyer(final User seller) {
		return new Specification<BuyerAgentMapping>() {
			public Predicate toPredicate(Root<BuyerAgentMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BuyerAgentMapping_.BUYER), seller);
			}
		};
	}
	public Specification<BuyerAgentMapping> hasBuyerId(final Long sellerId) {
		return new Specification<BuyerAgentMapping>() {
			public Predicate toPredicate(Root<BuyerAgentMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BuyerAgentMapping_.BUYER).get(BaseEntity_.ID), sellerId);
			}
		};
	}
	public Specification<BuyerAgentMapping> hasBuyerAgent(final User sellerAgent) {
		return new Specification<BuyerAgentMapping>() {
			public Predicate toPredicate(Root<BuyerAgentMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BuyerAgentMapping_.BUYER_AGENT), sellerAgent);
			}
		};
	}
	public Specification<BuyerAgentMapping> hasBuyerAgentId(final Long sellerId) {
		return new Specification<BuyerAgentMapping>() {
			public Predicate toPredicate(Root<BuyerAgentMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BuyerAgentMapping_.BUYER_AGENT).get(BaseEntity_.ID), sellerId);
			}
		};
	}
}
