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
import com.kirana.avatar.finance.dto.BankAccountDTO;
import com.kirana.avatar.finance.mapper.BankAccountMapper;
import com.kirana.avatar.finance.model.BankAccount;
import com.kirana.avatar.finance.repositories.BankAccountRepository;
import com.kirana.avatar.finance.service.BankAccountService;
import com.kirana.avatar.finance.specifications.BankAccountSpecification;
import com.kirana.avatar.master.dto.BankDTO;
import com.kirana.avatar.master.feign.BankClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, BankAccountDTO, BankAccountMapper, BankAccountRepository, BankAccountSpecification> implements BankAccountService {

	private BankAccountRepository bankAccountRepository;
	private BankAccountMapper bankAccountMapper;
	private BankAccountSpecification bankAccountSpecification;

	private UserClient userClient;
	private BankClient bankClient;
	private ObjectMapper objectMapper;

	public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, BankAccountMapper bankAccountMapper, 
			BankAccountSpecification bankAccountSpecification,
			UserClient userClient,
			BankClient bankClient,
			ObjectMapper objectMapper) {
		super(bankAccountRepository, bankAccountMapper, bankAccountSpecification);
		this.bankAccountRepository = bankAccountRepository;
		this.bankAccountMapper = bankAccountMapper;
		this.bankAccountSpecification = bankAccountSpecification;
		this.userClient = userClient;
		this.bankClient = bankClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected BankAccount beforeUpdate(BankAccountDTO bankAccountDTO, BankAccount model) {
		model.setUser((Long)bankAccountDTO.getUser().get(BaseEntity_.ID));
		model.setBank((Long)bankAccountDTO.getBank().get(BaseEntity_.ID));
		return model;
	}

	@Override
	protected BankAccount afterUpdate(BankAccountDTO bankAccountDTO, BankAccount model) {
		return model;
	}

	@Override
	protected Specification<BankAccount> getSpecification(FilterCriteria filter, Specification<BankAccount> specification) {
		return specification;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected BankAccountDTO afterLoad(BankAccountDTO resource, BankAccount model) {
		UserDTO userDTO = userClient.get(model.getUser());
		Map<String, Object> userMap = objectMapper.convertValue(userDTO, Map.class);
		BankDTO bankDTO = bankClient.get(model.getBank());
		Map<String, Object> bankMap = objectMapper.convertValue(bankDTO, Map.class);
		return resource
				.toBuilder()
				.user(userMap)
				.bank(bankMap)
				.build();
	}

	@Override
	protected BankAccount beforeSave(BankAccountDTO resource, BankAccount model) {
		return model;
	}

	@Override
	protected BankAccount afterSave(BankAccountDTO bankAccountDTO, BankAccount model) {
		return model;
	}
}
