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
package com.kirana.avatar.authorization.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.model.User_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __ArunPrakash__
 *
 */
@SuppressWarnings("serial")
@Component
public class UserSpecification extends BaseEntitySpecification<User> {

	public Specification<User> hasUserName(final String userName) {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(User_.USER_NAME), "%"+userName+"%");
			}
		};
	}
	public Specification<User> hasFirstName(final String firstName) {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(User_.FIRST_NAME), "%"+firstName+"%");
			}
		};
	}
	public Specification<User> hasLastName(final String lastName) {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(User_.LAST_NAME), "%"+lastName+"%");
			}
		};
	}
	public Specification<User> hasMobileNumber(final String mobileNumber) {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(User_.MOBILE_NUMBER), "%"+mobileNumber+"%");
			}
		};
	}
	public Specification<User> hasSuspended(final Boolean suspended) {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(User_.SUSPENDED), suspended);
			}
		};
	}
}
