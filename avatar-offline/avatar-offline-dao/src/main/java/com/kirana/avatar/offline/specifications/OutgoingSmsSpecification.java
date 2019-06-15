package com.kirana.avatar.offline.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.offline.model.OutgoingSms;
import com.kirana.avatar.offline.model.OutgoingSms_;
import com.kirana.avatar.offline.model.SmsProcessedStatus;
import com.kirana.avatar.offline.model.SmsProcessedStatus_;


/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class OutgoingSmsSpecification extends BaseEntitySpecification<OutgoingSms>{

	public Specification<OutgoingSms> hasOutgoingSmsStatus(SmsProcessedStatus notificationStatus) {
		return new Specification<OutgoingSms>() {
			public Predicate toPredicate(Root<OutgoingSms> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(OutgoingSms_.SMS_PROCESSED_STATUS), notificationStatus);
			}
		};
	}

	public Specification<OutgoingSms> hasOutgoingSmsStatus(Long notificationStatus) {
		return new Specification<OutgoingSms>() {
			public Predicate toPredicate(Root<OutgoingSms> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(OutgoingSms_.SMS_PROCESSED_STATUS).get(BaseEntity_.ID), notificationStatus);
			}
		};
	}

	public Specification<OutgoingSms> hasOutgoingSmsStatusNew() {
		return new Specification<OutgoingSms>() {
			public Predicate toPredicate(Root<OutgoingSms> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(OutgoingSms_.SMS_PROCESSED_STATUS).get(SmsProcessedStatus_.STATUS), "NEW");
			}
		};
	}
}
