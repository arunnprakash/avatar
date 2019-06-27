package com.kirana.avatar.finance.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.notification.dto.NotificationDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("NotificationService")
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api/notification"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface NotificationResource extends BaseResource<NotificationDTO> {

	@GetMapping(value= {"/send-price-update/{priceHistoryId}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public boolean sendPriceUpdateNotification(@PathVariable("priceHistoryId")Long priceHistoryId);
}
