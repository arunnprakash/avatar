package com.kirana.avatar.transaction.specifications;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.transaction.model.SellerTransactionStatus;
import com.kirana.avatar.transaction.model.SellerTransaction_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class SellerTransactionSpecification extends BaseEntitySpecification<SellerTransaction>{

	public Specification<SellerTransaction> hasWareHouseQuantityIsNull() {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNull(root.get(SellerTransaction_.WARE_HOUSE_PRODUCT_QUANTITY));
			}
		};
	}

	public Specification<SellerTransaction> hasWareHouseQualityIsNull() {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNull(root.get(SellerTransaction_.WARE_HOUSE_PRODUCT_QUALITY));
			}
		};
	}
	public Specification<SellerTransaction> hasSellerAgentProductQuantityIsNotNull() {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNotNull(root.get(SellerTransaction_.SELLER_AGENT_PRODUCT_QUANTITY));
			}
		};
	}

	public Specification<SellerTransaction> hasSellerAgentProductQualityIsNotNull() {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNotNull(root.get(SellerTransaction_.SELLER_AGENT_PRODUCT_QUALITY));
			}
		};
	}
	
	public Specification<SellerTransaction> hasSellerAgentProductQuantityIsNull() {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNull(root.get(SellerTransaction_.SELLER_AGENT_PRODUCT_QUANTITY));
			}
		};
	}

	public Specification<SellerTransaction> hasSellerAgentProductQualityIsNull() {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.isNull(root.get(SellerTransaction_.SELLER_AGENT_PRODUCT_QUALITY));
			}
		};
	}

	public Specification<SellerTransaction> hasSellerAgent(Long sellerAgentId) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.SELLER_AGENT), sellerAgentId);
			}
		};
	}
	
	public Specification<SellerTransaction> hasSeller(Long sellerId) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.SELLER), sellerId);
			}
		};
	}
	
	public Specification<SellerTransaction> hasWareHouse(Long wareHouseId) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.WARE_HOUSE), wareHouseId);
			}
		};
	}

	public Specification<SellerTransaction> hasTruckDriver(Long truckDriverId) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.TRUCK_DRIVER), truckDriverId);
			}
		};
	}

	public Specification<SellerTransaction> hasTransactionStatus(SellerTransactionStatus transactionStatus) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.TRANSACTION_STATUS), transactionStatus);
			}
		};
	}

	public Specification<SellerTransaction> hasTransactionStatus(Long transactionStatus) {
		return new Specification<SellerTransaction>() {
			public Predicate toPredicate(Root<SellerTransaction> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SellerTransaction_.TRANSACTION_STATUS).get(BaseEntity_.ID), transactionStatus);
			}
		};
	}
}
