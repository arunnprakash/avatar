/**
 * 
 */
package com.kirana.avatar.master.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.SellerMerchantCommissionDTO;
import com.kirana.avatar.master.mapper.SellerMerchantCommissionMapper;
import com.kirana.avatar.master.model.SellerMerchantCommission;
import com.kirana.avatar.master.repositories.TalukRepository;
import com.kirana.avatar.master.repositories.SellerMerchantCommissionRepository;
import com.kirana.avatar.master.service.SellerMerchantCommissionService;
import com.kirana.avatar.master.specifications.SellerMerchantCommissionSpecification;
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
public class SellerMerchantCommissionImpl extends BaseServiceImpl<SellerMerchantCommission, SellerMerchantCommissionDTO, SellerMerchantCommissionMapper, SellerMerchantCommissionRepository, SellerMerchantCommissionSpecification> implements SellerMerchantCommissionService{
	
	private SellerMerchantCommissionRepository sellerMerchantCommissionRepository;
	private SellerMerchantCommissionMapper sellerMerchantCommissionMapper;
	private SellerMerchantCommissionSpecification sellerMerchantCommissionSpecification;
	private TalukRepository talukRepository;
	
	public SellerMerchantCommissionImpl(SellerMerchantCommissionRepository sellerMerchantCommissionRepository, SellerMerchantCommissionMapper sellerMerchantCommissionMapper, SellerMerchantCommissionSpecification sellerMerchantCommissionSpecification,
			TalukRepository talukRepository) {
		super(sellerMerchantCommissionRepository, sellerMerchantCommissionMapper, sellerMerchantCommissionSpecification);
		this.sellerMerchantCommissionRepository = sellerMerchantCommissionRepository;
		this.sellerMerchantCommissionMapper = sellerMerchantCommissionMapper;
		this.sellerMerchantCommissionSpecification = sellerMerchantCommissionSpecification;
		this.talukRepository = talukRepository;
	}

	@Override
	protected SellerMerchantCommission beforeSave(SellerMerchantCommission model) {
		return model;
	}

	@Override
	protected SellerMerchantCommission beforeUpdate(SellerMerchantCommissionDTO sellerMerchantCommissionDTO, SellerMerchantCommission model) {
		return model;
	}

	@Override
	protected SellerMerchantCommission afterSave(SellerMerchantCommissionDTO sellerMerchantCommissionDTO, SellerMerchantCommission model) {
		return model;
	}

	@Override
	protected SellerMerchantCommission afterUpdate(SellerMerchantCommissionDTO sellerMerchantCommissionDTO, SellerMerchantCommission model) {
		return model;
	}

	@Override
	protected Specification<SellerMerchantCommission> getSpecification(FilterCriteria filter, Specification<SellerMerchantCommission> specification) {
		return specification;
	}

}
