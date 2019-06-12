package com.kirana.avatar.notification.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.notification.dto.NotificationDTO;
import com.kirana.avatar.notification.mapper.NotificationMapper;
import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.notification.repositories.NotificationRepository;
import com.kirana.avatar.notification.service.NotificationService;
import com.kirana.avatar.notification.specifications.NotificationSpecification;
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

	private UserClient userClient;
	private SellerPriceHistoryClient sellerPriceHistoryClient;
	private ObjectMapper objectMapper;
	public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper, 
			NotificationSpecification notificationSpecification, 
			UserClient userClient,
			SellerPriceHistoryClient sellerPriceHistoryClient,
			ObjectMapper objectMapper) {
		super(notificationRepository, notificationMapper, notificationSpecification);
		this.notificationRepository = notificationRepository;
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
}
