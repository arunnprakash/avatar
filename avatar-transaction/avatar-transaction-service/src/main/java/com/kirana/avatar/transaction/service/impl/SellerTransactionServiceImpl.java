package com.kirana.avatar.transaction.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.transaction.mapper.SellerTransactionMapper;
import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.transaction.repositories.SellerTransactionRepository;
import com.kirana.avatar.transaction.service.SellerTransactionService;
import com.kirana.avatar.transaction.specifications.SellerTransactionSpecification;
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
public class SellerTransactionServiceImpl extends BaseServiceImpl<SellerTransaction, SellerTransactionDTO, SellerTransactionMapper, SellerTransactionRepository, SellerTransactionSpecification> implements SellerTransactionService {

	private SellerTransactionRepository sellerTransactionRepository;
	private SellerTransactionMapper sellerTransactionMapper;
	private SellerTransactionSpecification sellerTransactionSpecification;
	public SellerTransactionServiceImpl(SellerTransactionRepository sellerTransactionRepository, SellerTransactionMapper sellerTransactionMapper, SellerTransactionSpecification sellerTransactionSpecification) {
		super(sellerTransactionRepository, sellerTransactionMapper, sellerTransactionSpecification);
		this.sellerTransactionRepository = sellerTransactionRepository;
		this.sellerTransactionMapper = sellerTransactionMapper;
		this.sellerTransactionSpecification = sellerTransactionSpecification;
	}

	@Override
	protected SellerTransaction beforeSave(SellerTransaction model) {
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

}
