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

import com.kirana.avatar.authorization.model.SellerAgentTruckDriverMapping;
import com.kirana.avatar.authorization.model.SellerAgentTruckDriverMapping_;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __ArunPrakash__
 *
 */
@SuppressWarnings("serial")
@Component
public class SellerAgentTruckDriverMappingSpecification extends BaseEntitySpecification<SellerAgentTruckDriverMapping> {

	public Specification<SellerAgentTruckDriverMapping> hasSellerAgent(final User sellerAgent) {
		return new Specification<SellerAgentTruckDriverMapping>() {
			public Predicate toPredicate(Root<SellerAgentTruckDriverMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerAgentTruckDriverMapping_.SELLER_AGENT), sellerAgent);
			}
		};
	}
	public Specification<SellerAgentTruckDriverMapping> hasSellerAgentId(final Long sellerAgentId) {
		return new Specification<SellerAgentTruckDriverMapping>() {
			public Predicate toPredicate(Root<SellerAgentTruckDriverMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerAgentTruckDriverMapping_.SELLER_AGENT).get(BaseEntity_.ID), sellerAgentId);
			}
		};
	}

	public Specification<SellerAgentTruckDriverMapping> hasTruckDriver(final User truckDriver) {
		return new Specification<SellerAgentTruckDriverMapping>() {
			public Predicate toPredicate(Root<SellerAgentTruckDriverMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerAgentTruckDriverMapping_.TRUCK_DRIVER), truckDriver);
			}
		};
	}
	public Specification<SellerAgentTruckDriverMapping> hasTruckDriverId(final Long truckDriverId) {
		return new Specification<SellerAgentTruckDriverMapping>() {
			public Predicate toPredicate(Root<SellerAgentTruckDriverMapping> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerAgentTruckDriverMapping_.TRUCK_DRIVER).get(BaseEntity_.ID), truckDriverId);
			}
		};
	}

}
