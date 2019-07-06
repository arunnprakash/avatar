package com.kirana.avatar.finance.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.finance.dto.WalletDTO;
import com.kirana.avatar.finance.mapper.WalletMapper;
import com.kirana.avatar.finance.model.Wallet;
import com.kirana.avatar.finance.repositories.WalletRepository;
import com.kirana.avatar.finance.service.WalletService;
import com.kirana.avatar.finance.specifications.WalletSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class WalletServiceImpl extends BaseServiceImpl<Wallet, WalletDTO, WalletMapper, WalletRepository, WalletSpecification> implements WalletService {

	private WalletRepository walletRepository;
	private WalletMapper walletMapper;
	private WalletSpecification walletSpecification;

	private UserClient userClient;
	private ObjectMapper objectMapper;

	public WalletServiceImpl(WalletRepository walletRepository, WalletMapper walletMapper, 
			WalletSpecification walletSpecification,
			UserClient userClient,
			ObjectMapper objectMapper) {
		super(walletRepository, walletMapper, walletSpecification);
		this.walletRepository = walletRepository;
		this.walletMapper = walletMapper;
		this.walletSpecification = walletSpecification;
		this.userClient = userClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected Wallet beforeUpdate(WalletDTO walletDTO, Wallet model) {
		model.setUser((Long)walletDTO.getUser().get(BaseEntity_.ID));
		return model;
	}

	@Override
	protected Wallet afterUpdate(WalletDTO walletDTO, Wallet model) {
		return model;
	}

	@Override
	protected Specification<Wallet> getSpecification(FilterCriteria filter, Specification<Wallet> specification) {
		return specification;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected WalletDTO afterLoad(WalletDTO resource, Wallet model) {
		UserDTO userDTO = userClient.get(model.getUser());
		Map<String, Object> userMap = objectMapper.convertValue(userDTO, Map.class);
		return resource
				.toBuilder()
				.user(userMap)
				.build();
	}

	@Override
	protected Wallet beforeSave(WalletDTO resource, Wallet model) {
		return model;
	}

	@Override
	protected Wallet afterSave(WalletDTO walletDTO, Wallet model) {
		return model;
	}

	@Override
	public WalletDTO transferAmountToUserBankAccount(Long userId, Double amount) {
		Specification<Wallet> specification = Specification.where(walletSpecification.hasDeleted(false));
		specification = specification.and(walletSpecification.hasUser(userId));
		Wallet wallet = walletRepository.findOne(specification).orElseThrow(ApiException::resourceNotFound);
		wallet.setBalance(wallet.getBalance() - amount);
		walletRepository.save(wallet);
		return walletMapper.toDTO(wallet);
	}

	@Override
	public WalletDTO transferAllAmountToUserBankAccount(Long userId) {
		Specification<Wallet> specification = Specification.where(walletSpecification.hasDeleted(false));
		specification = specification.and(walletSpecification.hasUser(userId));
		Wallet wallet = walletRepository.findOne(specification).orElseThrow(ApiException::resourceNotFound);
		wallet.setBalance(0d);
		walletRepository.save(wallet);
		return walletMapper.toDTO(wallet);
	}

	@Override
	public WalletDTO creditAmount(Long userId, Double amount) {
		Specification<Wallet> specification = Specification.where(walletSpecification.hasDeleted(false));
		specification = specification.and(walletSpecification.hasUser(userId));
		Wallet wallet = walletRepository.findOne(specification).orElseThrow(ApiException::resourceNotFound);
		wallet.setBalance(wallet.getBalance() + amount);
		walletRepository.save(wallet);
		return walletMapper.toDTO(wallet);
	}
}
