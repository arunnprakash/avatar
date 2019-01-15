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

import com.kirana.avatar.authorization.model.Country;
import com.kirana.avatar.authorization.model.Country_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class CountrySpecification extends BaseEntitySpecification<Country>{
	
	public Specification<Country> hasCountryCode(final String countryCode) {
		return new Specification<Country>() {
			public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Country_.COUNTRY_CODE), "%"+countryCode+"%");
			}

		};
	}

}
