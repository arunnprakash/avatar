package com.kirana.avatar.offline.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.offline.model.SmsProcessedStatus;
import com.kirana.avatar.offline.model.SmsProcessedStatus_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class SmsProcessedStatusSpecification extends BaseEntitySpecification<SmsProcessedStatus>{

	public Specification<SmsProcessedStatus> hasSmsProcessedStatus(String status) {
		return new Specification<SmsProcessedStatus>() {
			public Predicate toPredicate(Root<SmsProcessedStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SmsProcessedStatus_.STATUS), status);
			}
		};
	}

	public Specification<SmsProcessedStatus> hasStatusNew() {
		return new Specification<SmsProcessedStatus>() {
			public Predicate toPredicate(Root<SmsProcessedStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SmsProcessedStatus_.STATUS), "NEW");
			}
		};
	}
	
	public Specification<SmsProcessedStatus> hasStatusProcessing() {
		return new Specification<SmsProcessedStatus>() {
			public Predicate toPredicate(Root<SmsProcessedStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SmsProcessedStatus_.STATUS), "PROCESSING");
			}
		};
	}
	
	
	public Specification<SmsProcessedStatus> hasStatusCompleted() {
		return new Specification<SmsProcessedStatus>() {
			public Predicate toPredicate(Root<SmsProcessedStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(SmsProcessedStatus_.STATUS), "COMPLETED");
			}
		};
	}
}
