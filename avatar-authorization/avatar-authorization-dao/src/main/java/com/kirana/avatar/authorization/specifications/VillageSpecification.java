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

import com.kirana.avatar.authorization.model.Village;
import com.kirana.avatar.authorization.model.Village_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class VillageSpecification extends BaseEntitySpecification<Village> {
	
	public Specification<Village> hasVillageCode(final String villageCode) {
		return new Specification<Village>() {
			public Predicate toPredicate(Root<Village> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Village_.VILLAGE_CODE), "%"+villageCode+"%");
			}

		};
	}
	
	public Specification<Village> hasVillageName(final String villageName) {
		return new Specification<Village>() {
			public Predicate toPredicate(Root<Village> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Village_.VILLAGE_NAME), "%"+villageName+"%");
			}

		};
	}
	

}
