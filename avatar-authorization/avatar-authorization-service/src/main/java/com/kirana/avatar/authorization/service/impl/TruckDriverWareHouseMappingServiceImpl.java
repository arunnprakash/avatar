/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.TruckDriverWareHouseMappingDTO;
import com.kirana.avatar.authorization.mapper.TruckDriverWareHouseMapper;
import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping;
import com.kirana.avatar.authorization.repositories.TalukRepository;
import com.kirana.avatar.authorization.repositories.TruckDriverWareHouseMappingRepository;
import com.kirana.avatar.authorization.service.TruckDriverWareHouseMappingService;
import com.kirana.avatar.authorization.specifications.TruckDriverWareHouseMappingSpecification;
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
public class TruckDriverWareHouseMappingServiceImpl extends BaseServiceImpl<TruckDriverWareHouseMapping, TruckDriverWareHouseMappingDTO, TruckDriverWareHouseMapper, TruckDriverWareHouseMappingRepository, TruckDriverWareHouseMappingSpecification> implements TruckDriverWareHouseMappingService {
	
	private TruckDriverWareHouseMappingRepository wareHouseRepository;
	private TruckDriverWareHouseMapper truckDriverWareHouseMapper;
	private TruckDriverWareHouseMappingSpecification wareHouseSpecification;
	private TalukRepository talukRepository;
	
	public TruckDriverWareHouseMappingServiceImpl(TruckDriverWareHouseMappingRepository wareHouseRepository, TruckDriverWareHouseMapper truckDriverWareHouseMapper, TruckDriverWareHouseMappingSpecification wareHouseSpecification,
			TalukRepository talukRepository) {
		super(wareHouseRepository, truckDriverWareHouseMapper, wareHouseSpecification);
		this.wareHouseRepository = wareHouseRepository;
		this.truckDriverWareHouseMapper = truckDriverWareHouseMapper;
		this.wareHouseSpecification = wareHouseSpecification;
		this.talukRepository = talukRepository;
	}

	@Override
	protected TruckDriverWareHouseMapping beforeSave(TruckDriverWareHouseMapping model) {
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

}
