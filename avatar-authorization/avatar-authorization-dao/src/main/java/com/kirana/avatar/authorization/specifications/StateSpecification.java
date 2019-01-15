/**
 * 
 */
package com.kirana.avatar.authorization.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.authorization.model.State;
import com.kirana.avatar.authorization.model.State_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class StateSpecification extends BaseEntitySpecification<State>{
	
	public Specification<State> hasStateCode(final String stateCode) {
		return new Specification<State>() {
			public Predicate toPredicate(Root<State> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(State_.STATE_CODE), "%"+stateCode+"%");
			}

		};
	}

}
