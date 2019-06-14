package com.kirana.avatar.notification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.notification.model.NotificationStatus;
import com.kirana.avatar.notification.model.NotificationType;
import com.kirana.avatar.notification.repositories.NotificationRepository;
import com.kirana.avatar.notification.repositories.NotificationStatusRepository;
import com.kirana.avatar.notification.repositories.NotificationTypeRepository;
import com.kirana.avatar.notification.service.NotificationSender;
import com.kirana.avatar.notification.specifications.NotificationSpecification;
import com.kirana.avatar.notification.specifications.NotificationStatusSpecification;
import com.kirana.avatar.notification.specifications.NotificationTypeSpecification;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class NotificationScheduledTasks {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private NotificationSpecification notificationSpecification;
	@Autowired
	private NotificationTypeRepository notificationTypeRepository;
	@Autowired
	private NotificationStatusRepository notificationStatusRepository;
	@Autowired
	private NotificationTypeSpecification notificationTypeSpecification;
	@Autowired
	private NotificationStatusSpecification notificationStatusSpecification;
	@Autowired
	private ApplicationContext applicationContext;
	
	@Scheduled(fixedRate = 10000)
	public void scheduleTaskWithFixedRate() {
		
		Specification<Notification> notificationSpec = Specification.where(notificationSpecification.hasDeleted(false));
		notificationSpec = notificationSpec.and(notificationSpecification.hasNotificationStatusNew());
		notificationRepository
			.findAll(notificationSpec)
			.stream()
			.findFirst()
			.ifPresent(this::processNotification);
	}

	private void processNotification(Notification notification) {
		log.info("Notification Process Started - {}", dateTimeFormatter.format(LocalDateTime.now()));
		Specification<NotificationStatus> statusSpecification = Specification.where(notificationStatusSpecification.hasDeleted(false));
		statusSpecification = statusSpecification.and(notificationStatusSpecification.hasStatusProcessing());
		NotificationStatus notificationStatus = notificationStatusRepository
				.findOne(statusSpecification)
				.orElseThrow(ApiException::resourceNotFound);
		notification.setNotificationStatus(notificationStatus);
		notificationRepository.save(notification);
		
		NotificationType notificationType = notification.getNotificationType();
		String serviceName = notificationType.getType();
		NotificationSender notificationSender = (NotificationSender)applicationContext.getBean(serviceName);
		if (null != notificationSender) {
			notificationSender.send(notification);
		} else {
			
		}
		log.info("Notification Process Completed - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

}