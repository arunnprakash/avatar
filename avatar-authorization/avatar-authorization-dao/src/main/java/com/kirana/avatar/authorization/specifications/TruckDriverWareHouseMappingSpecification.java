/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
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

import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping;
import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping_;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.model.WareHouse;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __ArunPrakash__
 *
 */
@SuppressWarnings("serial")
@Component
public class TruckDriverWareHouseMappingSpecification extends BaseEntitySpecification<TruckDriverWareHouseMapping> {

	public Specification<TruckDriverWareHouseMapping> hasTruckDriver(final User truckDriver) {
		return new Specification<TruckDriverWareHouseMapping>() {
			public Predicate toPredicate(Root<TruckDriverWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(TruckDriverWareHouseMapping_.TRUCK_DRIVER), truckDriver);
			}
		};
	}
	public Specification<TruckDriverWareHouseMapping> hasTruckDriverId(final Long truckDriverId) {
		return new Specification<TruckDriverWareHouseMapping>() {
			public Predicate toPredicate(Root<TruckDriverWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(TruckDriverWareHouseMapping_.TRUCK_DRIVER).get(BaseEntity_.ID), truckDriverId);
			}
		};
	}

	public Specification<TruckDriverWareHouseMapping> hasWareHouse(final WareHouse wareHouse) {
		return new Specification<TruckDriverWareHouseMapping>() {
			public Predicate toPredicate(Root<TruckDriverWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(TruckDriverWareHouseMapping_.WARE_HOUSE), wareHouse);
			}
		};
	}
	public Specification<TruckDriverWareHouseMapping> hasWareHouseId(final Long wareHouseId) {
		return new Specification<TruckDriverWareHouseMapping>() {
			public Predicate toPredicate(Root<TruckDriverWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(TruckDriverWareHouseMapping_.WARE_HOUSE).get(BaseEntity_.ID), wareHouseId);
			}
		};
	}


}
