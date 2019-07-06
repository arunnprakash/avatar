/**
 * 
 */
package com.kirana.avatar.master.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.BankNameDTO;
import com.kirana.avatar.master.mapper.BankNameMapper;
import com.kirana.avatar.master.model.BankName;
import com.kirana.avatar.master.repositories.BankNameRepository;
import com.kirana.avatar.master.service.BankNameService;
import com.kirana.avatar.master.specifications.BankNameSpecification;
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
public class BankNameServiceImpl extends BaseServiceImpl<BankName, BankNameDTO, BankNameMapper, BankNameRepository, BankNameSpecification> implements BankNameService{
	
	private BankNameRepository bankNameRepository;
	private BankNameMapper bankNameMapper;
	private BankNameSpecification bankNameSpecification;
	
	public BankNameServiceImpl(BankNameRepository bankNameRepository, BankNameMapper bankNameMapper, BankNameSpecification bankNameSpecification) {
		super(bankNameRepository, bankNameMapper, bankNameSpecification);
		this.bankNameRepository = bankNameRepository;
		this.bankNameMapper = bankNameMapper;
		this.bankNameSpecification = bankNameSpecification;
	}

	@Override
	protected BankName beforeSave(BankNameDTO bankNameDTO, BankName model) {
		return model;
	}

	@Override
	protected BankName beforeUpdate(BankNameDTO bankNameDTO, BankName model) {
		return model;
	}

	@Override
	protected BankName afterSave(BankNameDTO bankNameDTO, BankName model) {
		return model;
	}

	@Override
	protected BankName afterUpdate(BankNameDTO bankNameDTO, BankName model) {
		return model;
	}

	@Override
	protected Specification<BankName> getSpecification(FilterCriteria filter, Specification<BankName> specification) {
		return specification;
	}

	@Override
	protected BankNameDTO afterLoad(BankNameDTO resource, BankName model) {
		return resource;
	}

}
