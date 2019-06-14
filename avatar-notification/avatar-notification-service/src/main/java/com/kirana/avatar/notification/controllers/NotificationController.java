package com.kirana.avatar.notification.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.notification.dto.NotificationDTO;
import com.kirana.avatar.notification.resource.NotificationResource;
import com.kirana.avatar.notification.service.NotificationService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class NotificationController extends BaseController<NotificationService, NotificationDTO> implements NotificationResource {

	private NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {
		super(notificationService);
		this.notificationService = notificationService;
	}

	@Override
	public boolean sendPriceUpdateNotification(Long priceHistoryId) {
		return this.notificationService.sendPriceUpdateNotification(priceHistoryId);
	}
}
