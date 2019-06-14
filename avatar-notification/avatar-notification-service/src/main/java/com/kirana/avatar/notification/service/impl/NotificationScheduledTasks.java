package com.kirana.avatar.notification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.notification.repositories.NotificationRepository;
import com.kirana.avatar.notification.repositories.NotificationStatusRepository;
import com.kirana.avatar.notification.repositories.NotificationTypeRepository;
import com.kirana.avatar.notification.specifications.NotificationSpecification;
import com.kirana.avatar.notification.specifications.NotificationStatusSpecification;
import com.kirana.avatar.notification.specifications.NotificationTypeSpecification;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
		log.info("Notification Process Completed - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

}