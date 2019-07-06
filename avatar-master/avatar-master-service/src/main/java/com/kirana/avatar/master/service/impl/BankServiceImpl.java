/**
 * 
 */
package com.kirana.avatar.master.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.BankDTO;
import com.kirana.avatar.master.mapper.BankMapper;
import com.kirana.avatar.master.model.Bank;
import com.kirana.avatar.master.repositories.BankRepository;
import com.kirana.avatar.master.repositories.VillageRepository;
import com.kirana.avatar.master.service.BankService;
import com.kirana.avatar.master.specifications.BankSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */

@Slf4j
@Service
@SuppressWarnings("unused")
public class BankServiceImpl extends BaseServiceImpl<Bank, BankDTO, BankMapper, BankRepository, BankSpecification> implements BankService{
	
	private BankRepository bankRepository;
	private BankMapper bankMapper;
	private BankSpecification bankSpecification;
	
	private VillageRepository villageRepository;

	public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper, BankSpecification bankSpecification, 
			VillageRepository villageRepository) {
		super(bankRepository, bankMapper, bankSpecification);
		this.bankRepository = bankRepository;
		this.bankMapper = bankMapper;
		this.bankSpecification = bankSpecification;
		this.villageRepository = villageRepository;
	}

	@Override
	protected Bank beforeSave(BankDTO bankDTO, Bank model) {
		return model;
	}

	@Override
	protected Bank beforeUpdate(BankDTO bankDTO, Bank model) {
		return villageRepository
				.findById(bankDTO.getVillage().getId())
				.map(village -> {
					model.setVillage(village);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	protected Bank afterSave(BankDTO bankDTO, Bank model) {
		return model;
	}

	@Override
	protected Bank afterUpdate(BankDTO bankDTO, Bank model) {
		return model;
	}

	@Override
	protected Specification<Bank> getSpecification(FilterCriteria filter, Specification<Bank> specification) {
		return specification;
	}

	@Override
	protected BankDTO afterLoad(BankDTO resource, Bank model) {
		return resource;
	}

}
