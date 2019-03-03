package com.kirana.avatar.transaction.service.impl;


import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.transaction.dto.SellerOrder;
import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.transaction.mapper.SellerTransactionMapper;
import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.transaction.repositories.SellerTransactionRepository;
import com.kirana.avatar.transaction.service.SellerTransactionService;
import com.kirana.avatar.transaction.specifications.SellerTransactionSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.dto.WareHouseDTO;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.PriceHistoryDTO;
import com.kirana.avatar.product.feign.PriceHistoryClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class SellerTransactionServiceImpl extends BaseServiceImpl<SellerTransaction, SellerTransactionDTO, SellerTransactionMapper, SellerTransactionRepository, SellerTransactionSpecification> implements SellerTransactionService {

	private SellerTransactionRepository sellerTransactionRepository;
	private SellerTransactionMapper sellerTransactionMapper;
	private SellerTransactionSpecification sellerTransactionSpecification;
	private UserClient userClient;
	private PriceHistoryClient priceHistoryClient;
	private ObjectMapper objectMapper;
	public SellerTransactionServiceImpl(SellerTransactionRepository sellerTransactionRepository, SellerTransactionMapper sellerTransactionMapper, 
			SellerTransactionSpecification sellerTransactionSpecification, 
			UserClient userClient,
			PriceHistoryClient priceHistoryClient,
			ObjectMapper objectMapper) {
		super(sellerTransactionRepository, sellerTransactionMapper, sellerTransactionSpecification);
		this.sellerTransactionRepository = sellerTransactionRepository;
		this.sellerTransactionMapper = sellerTransactionMapper;
		this.sellerTransactionSpecification = sellerTransactionSpecification;
		this.userClient = userClient;
		this.priceHistoryClient = priceHistoryClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected SellerTransaction beforeSave(SellerTransaction model) {
		UserDTO sellerAgent = userClient.getSellerAgentForSeller(model.getSeller());
		model.setSellerAgent(sellerAgent.getId());
		UserDTO truckDriver = userClient.getTruckDriverForSellerAgent(sellerAgent.getId());
		model.setTruckDriver(truckDriver.getId());
		WareHouseDTO wareHouseDTO = userClient.getWareHouseForTruckDriver(truckDriver.getId());
		model.setWareHouse(wareHouseDTO.getId());
		return model;
	}

	@Override
	protected SellerTransaction beforeUpdate(SellerTransactionDTO sellerTransactionDTO, SellerTransaction model) {
		return model;
	}

	@Override
	protected SellerTransaction afterSave(SellerTransactionDTO sellerTransactionDTO, SellerTransaction model) {
		return model;
	}

	@Override
	protected SellerTransaction afterUpdate(SellerTransactionDTO sellerTransactionDTO, SellerTransaction model) {
		return model;
	}

	@Override
	protected Specification<SellerTransaction> getSpecification(FilterCriteria filter, Specification<SellerTransaction> specification) {
		return specification;
	}

	@Override
	public List<SellerOrder> getOrdersForSellerAgent(Long sellerAgentId, String orderCreatedDate) {
		ZonedDateTime createdDate = ZonedDateTime.parse(orderCreatedDate, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.systemDefault()));
		log.debug("CreatedDate {}", createdDate);
		Specification<SellerTransaction> specification = Specification.where(sellerTransactionSpecification.hasDeleted(false));
		specification = specification.and(sellerTransactionSpecification.hasSellerAgent(sellerAgentId));
		specification = specification.and(sellerTransactionSpecification.hasCreatedDateBetween(createdDate, createdDate.plusHours(23).plusMinutes(59).plusSeconds(59)));
		return sellerTransactionRepository
				.findAll(specification)
				.stream()
				.map(sellerTransactionMapper::toDTO)
				.map(this::createSellerOrder)
				.collect(Collectors.toList());
	}
	@SuppressWarnings("unchecked")
	private SellerOrder createSellerOrder(SellerTransactionDTO sellerTransactionDTO) {
		log.debug("Seller Transaction {}", sellerTransactionDTO);
		PriceHistoryDTO priceHistoryDTO = priceHistoryClient.getPriceForProduct(sellerTransactionDTO.getProduct(), sellerTransactionDTO.getSellerProductQuality(), sellerTransactionDTO.getCreatedDate().format(DateTimeFormatter.ISO_INSTANT));
		SellerOrder sellerOrder = new SellerOrder();
		sellerOrder.setSellerTransaction(sellerTransactionDTO);
		Map<String, Object> priceTag = objectMapper.convertValue(priceHistoryDTO, Map.class);
		sellerOrder.setPriceTag(priceTag);
		return sellerOrder;
	}
}
