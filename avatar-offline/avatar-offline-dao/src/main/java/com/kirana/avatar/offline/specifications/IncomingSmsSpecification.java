package com.kirana.avatar.offline.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.offline.model.IncomingSms;
import com.kirana.avatar.offline.model.SmsProcessedStatus;
import com.kirana.avatar.offline.model.SmsProcessedStatus_;
import com.kirana.avatar.offline.model.IncomingSms_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class IncomingSmsSpecification extends BaseEntitySpecification<IncomingSms>{

	public Specification<IncomingSms> hasIncomingSmsStatus(SmsProcessedStatus notificationStatus) {
		return new Specification<IncomingSms>() {
			public Predicate toPredicate(Root<IncomingSms> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(IncomingSms_.SMS_PROCESSED_STATUS), notificationStatus);
			}
		};
	}

	public Specification<IncomingSms> hasIncomingSmsStatus(Long notificationStatus) {
		return new Specification<IncomingSms>() {
			public Predicate toPredicate(Root<IncomingSms> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(IncomingSms_.SMS_PROCESSED_STATUS).get(BaseEntity_.ID), notificationStatus);
			}
		};
	}

	public Specification<IncomingSms> hasIncomingSmsStatusNew() {
		return new Specification<IncomingSms>() {
			public Predicate toPredicate(Root<IncomingSms> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(IncomingSms_.SMS_PROCESSED_STATUS).get(SmsProcessedStatus_.STATUS), "NEW");
			}
		};
	}
}
