package com.kirana.avatar.notification.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.notification.dto.NotificationDTO;
import com.kirana.avatar.notification.mapper.NotificationMapper;
import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.notification.model.NotificationStatus;
import com.kirana.avatar.notification.model.NotificationType;
import com.kirana.avatar.notification.repositories.NotificationRepository;
import com.kirana.avatar.notification.repositories.NotificationStatusRepository;
import com.kirana.avatar.notification.repositories.NotificationTypeRepository;
import com.kirana.avatar.notification.service.NotificationService;
import com.kirana.avatar.notification.specifications.NotificationSpecification;
import com.kirana.avatar.notification.specifications.NotificationStatusSpecification;
import com.kirana.avatar.notification.specifications.NotificationTypeSpecification;
import com.kirana.avatar.product.feign.SellerPriceHistoryClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class NotificationServiceImpl extends BaseServiceImpl<Notification, NotificationDTO, NotificationMapper, NotificationRepository, NotificationSpecification> implements NotificationService {

	private NotificationRepository notificationRepository;
	private NotificationMapper notificationMapper;
	private NotificationSpecification notificationSpecification;

	private NotificationTypeRepository notificationTypeRepository;
	private NotificationStatusRepository notificationStatusRepository;
	private NotificationTypeSpecification notificationTypeSpecification;
	private NotificationStatusSpecification notificationStatusSpecification;
	
	private UserClient userClient;
	private SellerPriceHistoryClient sellerPriceHistoryClient;
	private ObjectMapper objectMapper;
	public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper, 
			NotificationSpecification notificationSpecification, 
			NotificationTypeRepository notificationTypeRepository, NotificationStatusRepository notificationStatusRepository, 
			NotificationTypeSpecification notificationTypeSpecification, NotificationStatusSpecification notificationStatusSpecification,
			UserClient userClient,
			SellerPriceHistoryClient sellerPriceHistoryClient,
			ObjectMapper objectMapper) {
		super(notificationRepository, notificationMapper, notificationSpecification);
		this.notificationRepository = notificationRepository;
		this.notificationTypeRepository = notificationTypeRepository;
		this.notificationStatusRepository = notificationStatusRepository;
		this.notificationTypeSpecification = notificationTypeSpecification;
		this.notificationStatusSpecification = notificationStatusSpecification;
		this.notificationMapper = notificationMapper;
		this.notificationSpecification = notificationSpecification;
		this.userClient = userClient;
		this.sellerPriceHistoryClient = sellerPriceHistoryClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected Notification beforeUpdate(NotificationDTO notificationDTO, Notification model) {
		return model;
	}

	@Override
	protected Notification afterUpdate(NotificationDTO notificationDTO, Notification model) {
		return model;
	}

	@Override
	protected Specification<Notification> getSpecification(FilterCriteria filter, Specification<Notification> specification) {
		return specification;
	}

	@Override
	protected NotificationDTO afterLoad(NotificationDTO resource, Notification model) {
		return resource;
	}

	@Override
	protected Notification beforeSave(NotificationDTO resource, Notification model) {
		return model;
	}

	@Override
	protected Notification afterSave(NotificationDTO notificationDTO, Notification model) {
		return model;
	}

	@Override
	public boolean sendPriceUpdateNotification(Long priceHistoryId) {
		try {
			Notification notification = new Notification();

			Specification<NotificationStatus> statusSpecification = Specification.where(notificationStatusSpecification.hasDeleted(false));
			statusSpecification = statusSpecification.and(notificationStatusSpecification.hasStatusNew());
			NotificationStatus notificationStatus = notificationStatusRepository
					.findOne(statusSpecification)
					.orElseThrow(ApiException::resourceNotFound);
			notification.setNotificationStatus(notificationStatus);

			Specification<NotificationType> typeSpecification = Specification.where(notificationTypeSpecification.hasDeleted(false));
			typeSpecification = typeSpecification.and(notificationTypeSpecification.hasSellerPriceNotificationType());
			NotificationType notificationType = notificationTypeRepository
					.findOne(typeSpecification)
					.orElseThrow(ApiException::resourceNotFound);
			notification.setNotificationType(notificationType);
			
			Map<String, Object> notificationData = new HashMap<>();
			notificationData.put("priceHistoryId", priceHistoryId);
			notification.setNotificationData(objectMapper.writeValueAsString(notificationData));
			this.notificationRepository.save(notification);
			return true;
		} catch (Exception exp) {
			log.error("Error while save send price notification for price history id " + priceHistoryId, exp);
		}
		return false;
	}
}
