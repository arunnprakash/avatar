package com.kirana.avatar.transaction.service.impl;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.feign.SellerPriceHistoryClient;
import com.kirana.avatar.transaction.dto.SellerOrder;
import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.transaction.mapper.SellerTransactionMapper;
import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.transaction.repositories.SellerTransactionRepository;
import com.kirana.avatar.transaction.service.SellerTransactionService;
import com.kirana.avatar.transaction.specifications.SellerTransactionSpecification;

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
	private SellerPriceHistoryClient sellerPriceHistoryClient;
	private ObjectMapper objectMapper;
	public SellerTransactionServiceImpl(SellerTransactionRepository sellerTransactionRepository, SellerTransactionMapper sellerTransactionMapper, 
			SellerTransactionSpecification sellerTransactionSpecification, 
			UserClient userClient,
			SellerPriceHistoryClient sellerPriceHistoryClient,
			ObjectMapper objectMapper) {
		super(sellerTransactionRepository, sellerTransactionMapper, sellerTransactionSpecification);
		this.sellerTransactionRepository = sellerTransactionRepository;
		this.sellerTransactionMapper = sellerTransactionMapper;
		this.sellerTransactionSpecification = sellerTransactionSpecification;
		this.userClient = userClient;
		this.sellerPriceHistoryClient = sellerPriceHistoryClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected SellerTransaction beforeSave(SellerTransactionDTO sellerTransactionDTO, SellerTransaction model) {
		UserDTO sellerAgent = userClient.getSellerAgentForSeller(model.getSeller());
		model.setSellerAgent(sellerAgent.getId());
		UserDTO truckDriver = userClient.getTruckDriverForSellerAgent(sellerAgent.getId());
		model.setTruckDriver(truckDriver.getId());
		Map<String, Object> wareHouseDTO = userClient.getWareHouseForTruckDriver(truckDriver.getId());
		model.setWareHouse((Long)wareHouseDTO.get(BaseEntity_.ID));
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
	public List<SellerOrder> getOrdersFromSeller(Long sellerId, String orderCreatedDate) {
		ZonedDateTime createdDate = ZonedDateTime.parse(orderCreatedDate, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.systemDefault()));
		createdDate = createdDate.truncatedTo(ChronoUnit.DAYS);
		log.debug("CreatedDate {}", createdDate);
		Specification<SellerTransaction> specification = Specification.where(sellerTransactionSpecification.hasDeleted(false));
		specification = specification.and(sellerTransactionSpecification.hasSeller(sellerId));
		specification = specification.and(sellerTransactionSpecification.hasCreatedDateBetween(createdDate, createdDate.plusHours(23).plusMinutes(59).plusSeconds(59)));
		return sellerTransactionRepository
				.findAll(specification)
				.stream()
				.map(sellerTransactionMapper::toDTO)
				.map(this::createSellerOrder)
				.collect(Collectors.toList());
	}

	@Override
	public List<SellerOrder> getOrdersForSellerAgent(Long sellerAgentId, String orderCreatedDate) {
		ZonedDateTime createdDate = ZonedDateTime.parse(orderCreatedDate, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.systemDefault()));
		createdDate = createdDate.truncatedTo(ChronoUnit.DAYS);
		log.debug("CreatedDate {}", createdDate);
		Specification<SellerTransaction> specification = Specification.where(sellerTransactionSpecification.hasDeleted(false));
		specification = specification.and(sellerTransactionSpecification.hasSellerAgentProductQualityIsNull());
		specification = specification.and(sellerTransactionSpecification.hasSellerAgentProductQuantityIsNull());
		specification = specification.and(sellerTransactionSpecification.hasSellerAgent(sellerAgentId));
		specification = specification.and(sellerTransactionSpecification.hasCreatedDateBetween(createdDate, createdDate.plusHours(23).plusMinutes(59).plusSeconds(59)));
		return sellerTransactionRepository
				.findAll(specification)
				.stream()
				.map(sellerTransactionMapper::toDTO)
				.map(this::createSellerOrder)
				.collect(Collectors.toList());
	}

	@Override
	public List<SellerOrder> getOrdersForSellerTruckDriver(Long truckDriverId, String orderCreatedDate) {
		UserDTO sellerAgent = userClient.getSellerAgentForTruckDriver(truckDriverId);
		return getOrdersForSellerAgent(sellerAgent.getId(), orderCreatedDate);
	}

	@Override
	public List<SellerOrder> getOrdersForWareHouse(Long wareHouseId, String orderCreatedDate) {
		ZonedDateTime createdDate = ZonedDateTime.parse(orderCreatedDate, DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.systemDefault()));
		createdDate = createdDate.truncatedTo(ChronoUnit.DAYS);
		log.debug("CreatedDate {}", createdDate);
		Specification<SellerTransaction> specification = Specification.where(sellerTransactionSpecification.hasDeleted(false));
		specification = specification.and(sellerTransactionSpecification.hasSellerAgentProductQualityIsNotNull());
		specification = specification.and(sellerTransactionSpecification.hasSellerAgentProductQuantityIsNotNull());
		specification = specification.and(sellerTransactionSpecification.hasWareHouseQualityIsNull());
		specification = specification.and(sellerTransactionSpecification.hasWareHouseQuantityIsNull());
		specification = specification.and(sellerTransactionSpecification.hasWareHouse(wareHouseId));
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
		SellerPriceHistoryDTO sellerPriceHistoryDTO = sellerPriceHistoryClient.getPriceForProduct(sellerTransactionDTO.getProduct(), sellerTransactionDTO.getSellerProductQuality(), sellerTransactionDTO.getCreatedDate().format(DateTimeFormatter.ISO_INSTANT));
		SellerOrder sellerOrder = new SellerOrder();
		sellerOrder.setSellerTransaction(sellerTransactionDTO);
		Map<String, Object> priceTag = objectMapper.convertValue(sellerPriceHistoryDTO, Map.class);
		sellerOrder.setPriceTag(priceTag);
		UserDTO sellerUserDTO = userClient.get(sellerTransactionDTO.getSeller());
		Map<String, Object> seller = objectMapper.convertValue(sellerUserDTO, Map.class);
		UserDTO sellerAgentUserDTO = userClient.get(sellerTransactionDTO.getSellerAgent());
		Map<String, Object> sellerAgent = objectMapper.convertValue(sellerAgentUserDTO, Map.class);
		sellerOrder.setSeller(seller);
		sellerOrder.setSellerAgent(sellerAgent);
		return sellerOrder;
	}

	@Override
	protected SellerTransactionDTO afterLoad(SellerTransactionDTO resource, SellerTransaction model) {
		return resource;
	}

}
