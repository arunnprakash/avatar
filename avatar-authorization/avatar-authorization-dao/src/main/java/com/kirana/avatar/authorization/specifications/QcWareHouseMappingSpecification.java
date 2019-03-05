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

import com.kirana.avatar.authorization.model.QcWareHouseMapping;
import com.kirana.avatar.authorization.model.QcWareHouseMapping_;
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
public class QcWareHouseMappingSpecification extends BaseEntitySpecification<QcWareHouseMapping> {

	public Specification<QcWareHouseMapping> hasQc(final User qc) {
		return new Specification<QcWareHouseMapping>() {
			public Predicate toPredicate(Root<QcWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(QcWareHouseMapping_.QC), qc);
			}
		};
	}
	public Specification<QcWareHouseMapping> hasQcId(final Long qcId) {
		return new Specification<QcWareHouseMapping>() {
			public Predicate toPredicate(Root<QcWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(QcWareHouseMapping_.QC).get(BaseEntity_.ID), qcId);
			}
		};
	}

	public Specification<QcWareHouseMapping> hasWareHouse(final WareHouse wareHouse) {
		return new Specification<QcWareHouseMapping>() {
			public Predicate toPredicate(Root<QcWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(QcWareHouseMapping_.WARE_HOUSE), wareHouse);
			}
		};
	}
	public Specification<QcWareHouseMapping> hasWareHouseId(final Long wareHouseId) {
		return new Specification<QcWareHouseMapping>() {
			public Predicate toPredicate(Root<QcWareHouseMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(QcWareHouseMapping_.WARE_HOUSE).get(BaseEntity_.ID), wareHouseId);
			}
		};
	}


}
