/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.QcWareHouseMappingDTO;
import com.kirana.avatar.authorization.mapper.QcWareHouseMapper;
import com.kirana.avatar.authorization.model.QcWareHouseMapping;
import com.kirana.avatar.authorization.repositories.QcWareHouseMappingRepository;
import com.kirana.avatar.authorization.service.QcWareHouseMappingService;
import com.kirana.avatar.authorization.specifications.QcWareHouseMappingSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.master.feign.TalukClient;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */

@Slf4j
@Service
@SuppressWarnings("unused")
public class QcWareHouseMappingServiceImpl extends BaseServiceImpl<QcWareHouseMapping, QcWareHouseMappingDTO, QcWareHouseMapper, QcWareHouseMappingRepository, QcWareHouseMappingSpecification> implements QcWareHouseMappingService {
	
	private QcWareHouseMappingRepository wareHouseRepository;
	private QcWareHouseMapper qcWareHouseMapper;
	private QcWareHouseMappingSpecification wareHouseSpecification;
	
	public QcWareHouseMappingServiceImpl(QcWareHouseMappingRepository wareHouseRepository, QcWareHouseMapper qcWareHouseMapper, QcWareHouseMappingSpecification wareHouseSpecification) {
		super(wareHouseRepository, qcWareHouseMapper, wareHouseSpecification);
		this.wareHouseRepository = wareHouseRepository;
		this.qcWareHouseMapper = qcWareHouseMapper;
		this.wareHouseSpecification = wareHouseSpecification;
	}

	@Override
	protected QcWareHouseMapping beforeSave(QcWareHouseMapping model) {
		return model;
	}

	@Override
	protected QcWareHouseMapping beforeUpdate(QcWareHouseMappingDTO wareHouseDTO, QcWareHouseMapping model) {
		return model;
	}

	@Override
	protected QcWareHouseMapping afterSave(QcWareHouseMappingDTO wareHouseDTO, QcWareHouseMapping model) {
		return model;
	}

	@Override
	protected QcWareHouseMapping afterUpdate(QcWareHouseMappingDTO wareHouseDTO, QcWareHouseMapping model) {
		return model;
	}

	@Override
	protected Specification<QcWareHouseMapping> getSpecification(FilterCriteria filter, Specification<QcWareHouseMapping> specification) {
		return specification;
	}

	@Override
	public QcWareHouseMappingDTO findByQcId(Long qcId) {
		Specification<QcWareHouseMapping> specification = Specification.where(wareHouseSpecification.hasDeleted(false));
		specification = specification.and(wareHouseSpecification.hasQcId(qcId));
		return wareHouseRepository
				.findOne(specification)
				.map(qcWareHouseMapper::toDTO)
				.orElseThrow(ApiException::resourceNotFound);
	}

}
