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

import com.kirana.avatar.master.model.District;
import com.kirana.avatar.master.model.District_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class DistrictSpecification extends BaseEntitySpecification<District>{
	
	public Specification<District> hasDistrictCode(final String DistrictCode) {
		return new Specification<District>() {
			public Predicate toPredicate(Root<District> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(District_.DISTRICT_CODE), "%"+DistrictCode+"%");
			}

		};
	}

}
