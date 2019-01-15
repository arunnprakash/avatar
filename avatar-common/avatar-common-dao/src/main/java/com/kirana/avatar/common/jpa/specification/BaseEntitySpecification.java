/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.jpa.specification;

import java.time.ZonedDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.kirana.avatar.common.jpa.entity.BaseEntity;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.entity.LocaleEntity_;

/**
 * @author __ArunPrakash__
 *
 */
@SuppressWarnings("serial")
public abstract class BaseEntitySpecification<Model extends BaseEntity<Model>> {

	public Specification<Model> hasId(final Long id) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BaseEntity_.ID), id);
			}
		};
	}
	
	public Specification<Model> hasDeletedNotNull() {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNotNull(root.get(BaseEntity_.DELETED));
			}
		};
	}

	public Specification<Model> hasDeleted(final boolean deleted) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BaseEntity_.DELETED), deleted);
			}
		};
	}

	public Specification<Model> hasCreatedDate(final ZonedDateTime date) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BaseEntity_.CREATED_DATE), date);
			}
		};
	}

	public Specification<Model> hasCreatedDateBetween(final ZonedDateTime date1, final ZonedDateTime date2) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.between(root.get(BaseEntity_.CREATED_DATE), date1, date2);
			}
		};
	}

	public Specification<Model> hasModifiedDate(final ZonedDateTime date) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BaseEntity_.LAST_MODIFIED_DATE), date);
			}
		};
	}

	public Specification<Model> hasModifiedDateBetween(final ZonedDateTime date1, final ZonedDateTime date2) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.between(root.get(BaseEntity_.LAST_MODIFIED_DATE), date1, date2);
			}
		};
	}

	public Specification<Model> hasCreatedBy(final String createdBy) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BaseEntity_.CREATED_BY), createdBy);
			}
		};
	}

	public Specification<Model> hasModifiedBy(final String modifiedBy) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(BaseEntity_.LAST_MODIFIED_BY), modifiedBy);
			}
		};
	}
	
	public Specification<Model> hasEnglish(final String en) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(LocaleEntity_.EN), "%"+en+"%");
			}

		};
	}
	
	public Specification<Model> hasTamil(final String ta) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(LocaleEntity_.TA), "%"+ta+"%");
			}
		};
	}
	
	public Specification<Model> hasMalayalam(final String ma) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(LocaleEntity_.MA), "%"+ma+"%");
			}
		};
	}
	
	public Specification<Model> hasKanadam(final String ka) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(LocaleEntity_.KA), "%"+ka+"%");
			}
		};
	}
	
	public Specification<Model> hasTelugu(final String te) {
		return new Specification<Model>() {
			public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(LocaleEntity_.TE), "%"+te+"%");
			}
		};
	}
}
