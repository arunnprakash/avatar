/**
 * 
 */
package com.kirana.avatar.notification.service.impl;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.notification.model.NotificationStatus;
import com.kirana.avatar.notification.repositories.NotificationRepository;
import com.kirana.avatar.notification.repositories.NotificationStatusRepository;
import com.kirana.avatar.notification.service.NotificationSender;
import com.kirana.avatar.notification.specifications.NotificationStatusSpecification;

/**
 * @author __ArunPrakash__
 *
 */
public abstract class AbstractNotificationSenderImpl implements NotificationSender {

	private NotificationRepository notificationRepository;
	private NotificationStatusSpecification notificationStatusSpecification;
	private NotificationStatusRepository notificationStatusRepository;
	private ObjectMapper objectMapper;

	public AbstractNotificationSenderImpl(NotificationRepository notificationRepository,
			NotificationStatusSpecification notificationStatusSpecification,
			NotificationStatusRepository notificationStatusRepository, ObjectMapper objectMapper) {
		super();
		this.notificationRepository = notificationRepository;
		this.notificationStatusSpecification = notificationStatusSpecification;
		this.notificationStatusRepository = notificationStatusRepository;
		this.objectMapper = objectMapper;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void send(Notification notification) throws Exception {
		updateNotifcationStatusAsProcessing(notification);
		prepareAndSend(notification, objectMapper.readValue(notification.getNotificationData(), Map.class));
		updateNotifcationStatusAsCompleted(notification);
	}

	protected abstract void prepareAndSend(Notification notification, Map<String, Object> notificationData);

	protected void updateNotifcationStatusAsCompleted(Notification notification) {
		updateNotifcationStatus(notification, notificationStatusSpecification.hasStatusCompleted());
	}

	protected void updateNotifcationStatusAsProcessing(Notification notification) {
		updateNotifcationStatus(notification, notificationStatusSpecification.hasStatusProcessing());
	}
	
	private void updateNotifcationStatus(Notification notification, Specification<NotificationStatus> notificationStatusSpec) {
		Specification<NotificationStatus> statusSpecification = Specification.where(notificationStatusSpecification.hasDeleted(false));
		statusSpecification = statusSpecification.and(notificationStatusSpec);
		NotificationStatus notificationStatus = notificationStatusRepository
				.findOne(statusSpecification)
				.orElseThrow(ApiException::resourceNotFound);
		notification.setNotificationStatus(notificationStatus);
		notificationRepository.save(notification);
	}
}
