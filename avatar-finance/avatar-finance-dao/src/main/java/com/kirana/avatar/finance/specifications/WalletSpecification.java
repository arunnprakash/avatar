package com.kirana.avatar.finance.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.finance.model.Wallet;
import com.kirana.avatar.finance.model.Wallet_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class WalletSpecification extends BaseEntitySpecification<Wallet>{

	public Specification<Wallet> hasUser(final Long user) {
		return new Specification<Wallet>() {
			public Predicate toPredicate(Root<Wallet> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Wallet_.USER), user);
			}
		};
	}

}
