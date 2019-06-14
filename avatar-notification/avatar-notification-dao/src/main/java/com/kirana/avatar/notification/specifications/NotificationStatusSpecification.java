package com.kirana.avatar.notification.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.notification.model.NotificationStatus;
import com.kirana.avatar.notification.model.NotificationStatus_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class NotificationStatusSpecification extends BaseEntitySpecification<NotificationStatus>{

	public Specification<NotificationStatus> hasNotificationStatus(String status) {
		return new Specification<NotificationStatus>() {
			public Predicate toPredicate(Root<NotificationStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(NotificationStatus_.STATUS), status);
			}
		};
	}

	public Specification<NotificationStatus> hasStatusNew() {
		return new Specification<NotificationStatus>() {
			public Predicate toPredicate(Root<NotificationStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(NotificationStatus_.STATUS), "NEW");
			}
		};
	}
	
	public Specification<NotificationStatus> hasStatusProcessing() {
		return new Specification<NotificationStatus>() {
			public Predicate toPredicate(Root<NotificationStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(NotificationStatus_.STATUS), "PROCESSING");
			}
		};
	}
	
	
	public Specification<NotificationStatus> hasStatusCompleted() {
		return new Specification<NotificationStatus>() {
			public Predicate toPredicate(Root<NotificationStatus> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(NotificationStatus_.STATUS), "COMPLETED");
			}
		};
	}
}
