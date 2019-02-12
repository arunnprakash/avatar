/**
 * 
 */
package com.kirana.avatar.product.specifications;

import java.time.ZonedDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.product.model.Holiday;
import com.kirana.avatar.product.model.Holiday_;

/**
 * @author __Telmila__
 *
 */
@SuppressWarnings("serial")
@Component
public class HolidaySpecification extends BaseEntitySpecification<Holiday>{
	
	public Specification<Holiday> hasStartDate(final ZonedDateTime startDate) {
		return new Specification<Holiday>() {
			public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Holiday_.START_DATE), "%"+startDate+"%");
			}
		};
	}
	
	public Specification<Holiday> hasEndDate(final ZonedDateTime endDate) {
		return new Specification<Holiday>() {
			public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Holiday_.END_DATE), "%"+endDate+"%");
			}
		};
	}
	
	public Specification<Holiday> hasDescription(final String description) {
		return new Specification<Holiday>() {
			public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Holiday_.DESCRIPTION), "%"+description+"%");
			}
		};
	}
	
	public Specification<Holiday> hasStateId(final Long state) {
		return new Specification<Holiday>() {
			public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Holiday_.STATE), "%"+state+"%");
			}
		};
	}
	
	public Specification<Holiday> hasDistrictId(final Long district) {
		return new Specification<Holiday>() {
			public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Holiday_.DISTRICT), "%"+district+"%");
			}
		};
	}
	
	public Specification<Holiday> hasTalukId(final Long taluk) {
		return new Specification<Holiday>() {
			public Predicate toPredicate(Root<Holiday> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Holiday_.TALUK), "%"+taluk+"%");
			}
		};
	}

}
