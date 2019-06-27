/**
 * 
 */
package com.kirana.avatar.finance.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.finance.model.Notification;
import com.kirana.avatar.finance.repositories.NotificationRepository;
import com.kirana.avatar.finance.repositories.NotificationStatusRepository;
import com.kirana.avatar.finance.specifications.NotificationStatusSpecification;
import com.kirana.avatar.master.feign.WareHouseClient;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.feign.SellerPriceHistoryClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service("SELLER_PRICE_NOTIFICATION")
public class SellerPriceNotificationSenderImpl extends AbstractNotificationSenderImpl {

	private SellerPriceHistoryClient sellerPriceHistoryClient;
	private WareHouseClient wareHouseClient;
	private UserClient userClient;

	public SellerPriceNotificationSenderImpl(NotificationRepository notificationRepository, 
			NotificationStatusSpecification notificationStatusSpecification, 
			NotificationStatusRepository notificationStatusRepository,
			ObjectMapper objectMapper,
			SellerPriceHistoryClient sellerPriceHistoryClient, 
			WareHouseClient wareHouseClient, 
			UserClient userClient) {
		super(notificationRepository, notificationStatusSpecification, notificationStatusRepository, objectMapper);
		this.sellerPriceHistoryClient = sellerPriceHistoryClient;
		this.wareHouseClient = wareHouseClient;
		this.userClient = userClient;
	}

	@Override
	protected void prepareAndSend(Notification notification, Map<String, Object> notificationData) {
		log.debug("PrepareAndSend Notification {}", notification);
		Number priceHistoryId = (Number)notificationData.get("priceHistoryId");
		SellerPriceHistoryDTO sellerPriceHistoryDTO = sellerPriceHistoryClient.get(priceHistoryId.longValue());
		Map<String, Object> market = sellerPriceHistoryDTO.getMarketPrice().getMarket();
		Number marketId = (Number)market.get(BaseEntity_.ID);
		List<Boolean> result = wareHouseClient.getWareHousesByMarket(marketId.longValue())
		.stream()
		.map(wareHouse -> {
			log.debug("Sending Notification for WareHouse {}", wareHouse);
			return wareHouse.getId();
		})
		.map(userClient::getSellerAgentByWareHouse)
		.map(sellerAgents -> {
			log.debug("WareHouse Having SellerAgents {}", sellerAgents);
			sellerAgents.forEach(sellerAgent -> {
				log.debug("Sending Notification to Sellers belongs to SellerAgent {}", sellerAgent);
				List<UserDTO> sellers = userClient.getSellersBySellerAgent(sellerAgent.getId());
				List<String> mobileNumbers = sellers
					.stream()
					.map(UserDTO::getMobileNumber)
					.collect(Collectors.toList());
				this.publishPrice(mobileNumbers, sellerPriceHistoryDTO);
			});
			return true;
		}).collect(Collectors.toList());
		log.debug("PrepareAndSend Notification Done with Result {}", result);
	}

	private boolean publishPrice(List<String> mobileNumbers, SellerPriceHistoryDTO sellerPriceHistoryDTO) {
		log.info("Sending SellerPrice {} Sms for mobileNumbers {} ", sellerPriceHistoryDTO.getPrice(), mobileNumbers);
		return false;
	}
}
