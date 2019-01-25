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
import com.kirana.avatar.product.model.Quality;
import com.kirana.avatar.product.model.Quality_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class QualitySpecification extends BaseEntitySpecification<Quality>{
	
	public Specification<Quality> hasQualityType(final String qualityType) {
		return new Specification<Quality>() {
			public Predicate toPredicate(Root<Quality> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Quality_.QUALITY_TYPE), "%"+qualityType+"%");
			}
		};
	}

}
