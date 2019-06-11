/**
 * 
 */
package com.kirana.avatar.master.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.SellerAgentCommissionDTO;
import com.kirana.avatar.master.mapper.SellerAgentCommissionMapper;
import com.kirana.avatar.master.model.SellerAgentCommission;
import com.kirana.avatar.master.repositories.TalukRepository;
import com.kirana.avatar.master.repositories.SellerAgentCommissionRepository;
import com.kirana.avatar.master.service.SellerAgentCommissionService;
import com.kirana.avatar.master.specifications.SellerAgentCommissionSpecification;
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
public class SellerAgentCommissionImpl extends BaseServiceImpl<SellerAgentCommission, SellerAgentCommissionDTO, SellerAgentCommissionMapper, SellerAgentCommissionRepository, SellerAgentCommissionSpecification> implements SellerAgentCommissionService{
	
	private SellerAgentCommissionRepository sellerAgentCommissionRepository;
	private SellerAgentCommissionMapper sellerAgentCommissionMapper;
	private SellerAgentCommissionSpecification sellerAgentCommissionSpecification;
	private TalukRepository talukRepository;
	
	public SellerAgentCommissionImpl(SellerAgentCommissionRepository sellerAgentCommissionRepository, SellerAgentCommissionMapper sellerAgentCommissionMapper, SellerAgentCommissionSpecification sellerAgentCommissionSpecification,
			TalukRepository talukRepository) {
		super(sellerAgentCommissionRepository, sellerAgentCommissionMapper, sellerAgentCommissionSpecification);
		this.sellerAgentCommissionRepository = sellerAgentCommissionRepository;
		this.sellerAgentCommissionMapper = sellerAgentCommissionMapper;
		this.sellerAgentCommissionSpecification = sellerAgentCommissionSpecification;
		this.talukRepository = talukRepository;
	}

	@Override
	protected SellerAgentCommission beforeSave(SellerAgentCommissionDTO sellerAgentCommissionDTO, SellerAgentCommission model) {
		return model;
	}

	@Override
	protected SellerAgentCommission beforeUpdate(SellerAgentCommissionDTO sellerAgentCommissionDTO, SellerAgentCommission model) {
		return model;
	}

	@Override
	protected SellerAgentCommission afterSave(SellerAgentCommissionDTO sellerAgentCommissionDTO, SellerAgentCommission model) {
		return model;
	}

	@Override
	protected SellerAgentCommission afterUpdate(SellerAgentCommissionDTO sellerAgentCommissionDTO, SellerAgentCommission model) {
		return model;
	}

	@Override
	protected Specification<SellerAgentCommission> getSpecification(FilterCriteria filter, Specification<SellerAgentCommission> specification) {
		return specification;
	}

	@Override
	protected SellerAgentCommissionDTO afterLoad(SellerAgentCommissionDTO resource, SellerAgentCommission model) {
		return resource;
	}

	@Override
	public SellerAgentCommissionDTO getLatestSellerAgentCommission() {
		Specification<SellerAgentCommission> specification = Specification.where(sellerAgentCommissionSpecification.hasDeleted(false));
		return sellerAgentCommissionRepository
				.findOne(specification)
				.map(sellerAgentCommissionMapper::toDTO)
				.orElseThrow(ApiException::resourceNotFound);
	}

}
