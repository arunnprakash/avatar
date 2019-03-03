package com.kirana.avatar.transaction.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.transaction.model.SellerTransaction_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class SellerTransactionSpecification extends BaseEntitySpecification<SellerTransaction>{

	public Specification<SellerTransaction> hasSellerAgent(Long sellerAgentId) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.SELLER_AGENT), sellerAgentId);
			}
		};
	}

}
