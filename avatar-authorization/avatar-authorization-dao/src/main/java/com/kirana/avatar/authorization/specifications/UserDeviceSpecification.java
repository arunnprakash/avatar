package com.kirana.avatar.authorization.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.authorization.model.UserDevice;
import com.kirana.avatar.authorization.model.UserDevice_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author _Telmila_
 *
 */
@SuppressWarnings("serial")
@Component
public class UserDeviceSpecification extends BaseEntitySpecification<UserDevice> {

	public Specification<UserDevice> hasModelName(final String modelName) {
		return new Specification<UserDevice>() {
			public Predicate toPredicate(Root<UserDevice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(UserDevice_.MODEL_NAME), "%"+modelName+"%");
			}
		};
	}
	public Specification<UserDevice> hasManufacturer(final String manufacturer) {
		return new Specification<UserDevice>() {
			public Predicate toPredicate(Root<UserDevice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(UserDevice_.MANUFACTURER), "%"+manufacturer+"%");
			}
		};
	}
	public Specification<UserDevice> hasImeiNumber(final String imeiNumber) {
		return new Specification<UserDevice>() {
			public Predicate toPredicate(Root<UserDevice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(UserDevice_.IMEI_NUMBER), "%"+imeiNumber+"%");
			}
		};
	}
	public Specification<UserDevice> hasLoggedIn(final Boolean loggedIn) {
		return new Specification<UserDevice>() {
			public Predicate toPredicate(Root<UserDevice> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(UserDevice_.LOGGED_IN), loggedIn);
			}
		};
	}
}
