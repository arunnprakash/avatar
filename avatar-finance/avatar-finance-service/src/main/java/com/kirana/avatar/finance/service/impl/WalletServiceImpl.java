package com.kirana.avatar.finance.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.finance.dto.WalletDTO;
import com.kirana.avatar.finance.mapper.WalletMapper;
import com.kirana.avatar.finance.model.Wallet;
import com.kirana.avatar.finance.repositories.WalletRepository;
import com.kirana.avatar.finance.service.WalletService;
import com.kirana.avatar.finance.specifications.WalletSpecification;
import com.kirana.avatar.product.feign.SellerPriceHistoryClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class WalletServiceImpl extends BaseServiceImpl<Wallet, WalletDTO, WalletMapper, WalletRepository, WalletSpecification> implements WalletService {

	private WalletRepository notificationRepository;
	private WalletMapper notificationMapper;
	private WalletSpecification notificationSpecification;

	private UserClient userClient;
	private SellerPriceHistoryClient sellerPriceHistoryClient;
	private ObjectMapper objectMapper;
	public WalletServiceImpl(WalletRepository notificationRepository, WalletMapper notificationMapper, 
			WalletSpecification notificationSpecification,
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
	protected Wallet beforeUpdate(WalletDTO notificationDTO, Wallet model) {
		return model;
	}

	@Override
	protected Wallet afterUpdate(WalletDTO notificationDTO, Wallet model) {
		return model;
	}

	@Override
	protected Specification<Wallet> getSpecification(FilterCriteria filter, Specification<Wallet> specification) {
		return specification;
	}

	@Override
	protected WalletDTO afterLoad(WalletDTO resource, Wallet model) {
		return resource;
	}

	@Override
	protected Wallet beforeSave(WalletDTO resource, Wallet model) {
		return model;
	}

	@Override
	protected Wallet afterSave(WalletDTO notificationDTO, Wallet model) {
		return model;
	}
}
