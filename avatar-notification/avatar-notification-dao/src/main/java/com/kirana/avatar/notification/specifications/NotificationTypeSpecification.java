package com.kirana.avatar.notification.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.notification.model.NotificationType;
import com.kirana.avatar.notification.model.NotificationType_;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class NotificationTypeSpecification extends BaseEntitySpecification<NotificationType>{

	public Specification<NotificationType> hasNotificationType(String type) {
		return new Specification<NotificationType>() {
			public Predicate toPredicate(Root<NotificationType> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(NotificationType_.TYPE), type);
			}
		};
	}

	public Specification<NotificationType> hasSellerPriceNotificationType() {
		return new Specification<NotificationType>() {
			public Predicate toPredicate(Root<NotificationType> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.equal(root.get(NotificationType_.TYPE), "SELLER_PRICE_NOTIFICATION");
			}
		};
	}
}
