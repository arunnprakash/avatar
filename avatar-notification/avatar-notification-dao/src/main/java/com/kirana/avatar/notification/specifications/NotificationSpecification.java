package com.kirana.avatar.notification.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.notification.model.NotificationStatus;
import com.kirana.avatar.notification.model.NotificationStatus_;
import com.kirana.avatar.notification.model.Notification_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class NotificationSpecification extends BaseEntitySpecification<Notification>{

	public Specification<Notification> hasNotificationStatus(NotificationStatus notificationStatus) {
		return new Specification<Notification>() {
			public Predicate toPredicate(Root<Notification> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Notification_.NOTIFICATION_STATUS), notificationStatus);
			}
		};
	}

	public Specification<Notification> hasNotificationStatus(Long notificationStatus) {
		return new Specification<Notification>() {
			public Predicate toPredicate(Root<Notification> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Notification_.NOTIFICATION_STATUS).get(BaseEntity_.ID), notificationStatus);
			}
		};
	}

	public Specification<Notification> hasNotificationStatusNew() {
		return new Specification<Notification>() {
			public Predicate toPredicate(Root<Notification> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(Notification_.NOTIFICATION_STATUS).get(NotificationStatus_.STATUS), "NEW");
			}
		};
	}
}
