/**
 * 
 */
package com.kirana.avatar.master.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.SellerTransportationChargeDTO;
import com.kirana.avatar.master.mapper.SellerTransportationChargeMapper;
import com.kirana.avatar.master.model.SellerTransportationCharge;
import com.kirana.avatar.master.repositories.TalukRepository;
import com.kirana.avatar.master.repositories.SellerTransportationChargeRepository;
import com.kirana.avatar.master.service.SellerTransportationChargeService;
import com.kirana.avatar.master.specifications.SellerTransportationChargeSpecification;
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
public class SellerTransportationChargeImpl extends BaseServiceImpl<SellerTransportationCharge, SellerTransportationChargeDTO, SellerTransportationChargeMapper, SellerTransportationChargeRepository, SellerTransportationChargeSpecification> implements SellerTransportationChargeService{
	
	private SellerTransportationChargeRepository sellerTransportationChargeRepository;
	private SellerTransportationChargeMapper sellerTransportationChargeMapper;
	private SellerTransportationChargeSpecification sellerTransportationChargeSpecification;
	private TalukRepository talukRepository;
	
	public SellerTransportationChargeImpl(SellerTransportationChargeRepository sellerTransportationChargeRepository, SellerTransportationChargeMapper sellerTransportationChargeMapper, SellerTransportationChargeSpecification sellerTransportationChargeSpecification,
			TalukRepository talukRepository) {
		super(sellerTransportationChargeRepository, sellerTransportationChargeMapper, sellerTransportationChargeSpecification);
		this.sellerTransportationChargeRepository = sellerTransportationChargeRepository;
		this.sellerTransportationChargeMapper = sellerTransportationChargeMapper;
		this.sellerTransportationChargeSpecification = sellerTransportationChargeSpecification;
		this.talukRepository = talukRepository;
	}

	@Override
	protected SellerTransportationCharge beforeSave(SellerTransportationChargeDTO sellerTransportationChargeDTO, SellerTransportationCharge model) {
		return model;
	}

	@Override
	protected SellerTransportationCharge beforeUpdate(SellerTransportationChargeDTO sellerTransportationChargeDTO, SellerTransportationCharge model) {
		return model;
	}

	@Override
	protected SellerTransportationCharge afterSave(SellerTransportationChargeDTO sellerTransportationChargeDTO, SellerTransportationCharge model) {
		return model;
	}

	@Override
	protected SellerTransportationCharge afterUpdate(SellerTransportationChargeDTO sellerTransportationChargeDTO, SellerTransportationCharge model) {
		return model;
	}

	@Override
	protected Specification<SellerTransportationCharge> getSpecification(FilterCriteria filter, Specification<SellerTransportationCharge> specification) {
		return specification;
	}

}
