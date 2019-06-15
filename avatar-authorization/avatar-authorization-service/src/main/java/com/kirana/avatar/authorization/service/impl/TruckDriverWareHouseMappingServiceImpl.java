/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;


import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.dto.TruckDriverWareHouseMappingDTO;
import com.kirana.avatar.authorization.mapper.TruckDriverWareHouseMapper;
import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping;
import com.kirana.avatar.authorization.repositories.TruckDriverWareHouseMappingRepository;
import com.kirana.avatar.authorization.service.TruckDriverWareHouseMappingService;
import com.kirana.avatar.authorization.specifications.TruckDriverWareHouseMappingSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
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
public class TruckDriverWareHouseMappingServiceImpl extends BaseServiceImpl<TruckDriverWareHouseMapping, TruckDriverWareHouseMappingDTO, TruckDriverWareHouseMapper, TruckDriverWareHouseMappingRepository, TruckDriverWareHouseMappingSpecification> implements TruckDriverWareHouseMappingService {
	
	private TruckDriverWareHouseMappingRepository wareHouseRepository;
	private TruckDriverWareHouseMapper truckDriverWareHouseMapper;
	private TruckDriverWareHouseMappingSpecification wareHouseSpecification;
	private WareHouseClient wareHouseClient;
	private ObjectMapper objectMapper;
	
	public TruckDriverWareHouseMappingServiceImpl(TruckDriverWareHouseMappingRepository wareHouseRepository, TruckDriverWareHouseMapper truckDriverWareHouseMapper, TruckDriverWareHouseMappingSpecification wareHouseSpecification, 
			WareHouseClient wareHouseClient,
			ObjectMapper objectMapper) {
		super(wareHouseRepository, truckDriverWareHouseMapper, wareHouseSpecification);
		this.wareHouseRepository = wareHouseRepository;
		this.truckDriverWareHouseMapper = truckDriverWareHouseMapper;
		this.wareHouseSpecification = wareHouseSpecification;
		this.wareHouseClient = wareHouseClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected TruckDriverWareHouseMapping beforeSave(TruckDriverWareHouseMappingDTO wareHouseDTO, TruckDriverWareHouseMapping model) {
		Number wareHouseId = (Number)wareHouseDTO.getWareHouse().get(BaseEntity_.ID);
		model.setWareHouse(wareHouseId.longValue());
		return model;
	}

	@Override
	protected TruckDriverWareHouseMapping beforeUpdate(TruckDriverWareHouseMappingDTO wareHouseDTO, TruckDriverWareHouseMapping model) {
		return model;
	}

	@Override
	protected TruckDriverWareHouseMapping afterSave(TruckDriverWareHouseMappingDTO wareHouseDTO, TruckDriverWareHouseMapping model) {
		return model;
	}

	@Override
	protected TruckDriverWareHouseMapping afterUpdate(TruckDriverWareHouseMappingDTO wareHouseDTO, TruckDriverWareHouseMapping model) {
		return model;
	}

	@Override
	protected Specification<TruckDriverWareHouseMapping> getSpecification(FilterCriteria filter, Specification<TruckDriverWareHouseMapping> specification) {
		return specification;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected TruckDriverWareHouseMappingDTO afterLoad(TruckDriverWareHouseMappingDTO resource,
			TruckDriverWareHouseMapping model) {
		WareHouseDTO wareHouseDTO = wareHouseClient.get(model.getWareHouse());
		return resource
				.toBuilder()
				.wareHouse(objectMapper.convertValue(wareHouseDTO, Map.class))
				.build();
	}

}
