/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;


import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.SellerAgentWareHouseMappingDTO;
import com.kirana.avatar.authorization.mapper.SellerAgentWareHouseMapper;
import com.kirana.avatar.authorization.model.SellerAgentWareHouseMapping;
import com.kirana.avatar.authorization.repositories.SellerAgentWareHouseMappingRepository;
import com.kirana.avatar.authorization.service.SellerAgentWareHouseMappingService;
import com.kirana.avatar.authorization.specifications.SellerAgentWareHouseMappingSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.master.dto.WareHouseDTO;
import com.kirana.avatar.master.feign.WareHouseClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */

@Slf4j
@Service
@SuppressWarnings("unused")
public class SellerAgentWareHouseMappingServiceImpl extends BaseServiceImpl<SellerAgentWareHouseMapping, SellerAgentWareHouseMappingDTO, SellerAgentWareHouseMapper, SellerAgentWareHouseMappingRepository, SellerAgentWareHouseMappingSpecification> implements SellerAgentWareHouseMappingService {
	
	private SellerAgentWareHouseMappingRepository wareHouseRepository;
	private SellerAgentWareHouseMapper sellerAgentWareHouseMapper;
	private SellerAgentWareHouseMappingSpecification wareHouseSpecification;
	private WareHouseClient wareHouseClient;
	private ObjectMapper objectMapper;
	
	public SellerAgentWareHouseMappingServiceImpl(SellerAgentWareHouseMappingRepository wareHouseRepository, SellerAgentWareHouseMapper sellerAgentWareHouseMapper, SellerAgentWareHouseMappingSpecification wareHouseSpecification, 
			WareHouseClient wareHouseClient, 
			ObjectMapper objectMapper) {
		super(wareHouseRepository, sellerAgentWareHouseMapper, wareHouseSpecification);
		this.wareHouseRepository = wareHouseRepository;
		this.sellerAgentWareHouseMapper = sellerAgentWareHouseMapper;
		this.wareHouseSpecification = wareHouseSpecification;
		this.wareHouseClient = wareHouseClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected SellerAgentWareHouseMapping beforeSave(SellerAgentWareHouseMappingDTO wareHouseDTO, SellerAgentWareHouseMapping model) {
		Number wareHouseId = (Number)wareHouseDTO.getWareHouse().get(BaseEntity_.ID);
		model.setWareHouse(wareHouseId.longValue());
		return model;
	}

	@Override
	protected SellerAgentWareHouseMapping beforeUpdate(SellerAgentWareHouseMappingDTO wareHouseDTO, SellerAgentWareHouseMapping model) {
		return model;
	}

	@Override
	protected SellerAgentWareHouseMapping afterSave(SellerAgentWareHouseMappingDTO wareHouseDTO, SellerAgentWareHouseMapping model) {
		return model;
	}

	@Override
	protected SellerAgentWareHouseMapping afterUpdate(SellerAgentWareHouseMappingDTO wareHouseDTO, SellerAgentWareHouseMapping model) {
		return model;
	}

	@Override
	protected Specification<SellerAgentWareHouseMapping> getSpecification(FilterCriteria filter, Specification<SellerAgentWareHouseMapping> specification) {
		return specification;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected SellerAgentWareHouseMappingDTO afterLoad(SellerAgentWareHouseMappingDTO resource,
			SellerAgentWareHouseMapping model) {
		WareHouseDTO wareHouseDTO = wareHouseClient.get(model.getWareHouse());
		return resource
				.toBuilder()
				.wareHouse(objectMapper.convertValue(wareHouseDTO, Map.class))
				.build();
	}

}
