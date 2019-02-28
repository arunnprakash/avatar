/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.WareHouseDTO;
import com.kirana.avatar.authorization.mapper.WareHouseMapper;
import com.kirana.avatar.authorization.model.WareHouse;
import com.kirana.avatar.authorization.repositories.TalukRepository;
import com.kirana.avatar.authorization.repositories.WareHouseRepository;
import com.kirana.avatar.authorization.service.WareHouseService;
import com.kirana.avatar.authorization.specifications.WareHouseSpecification;
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
public class WareHouseServiceImpl extends BaseServiceImpl<WareHouse, WareHouseDTO, WareHouseMapper, WareHouseRepository, WareHouseSpecification> implements WareHouseService{
	
	private WareHouseRepository wareHouseRepository;
	private WareHouseMapper wareHouseMapper;
	private WareHouseSpecification wareHouseSpecification;
	private TalukRepository talukRepository;
	
	public WareHouseServiceImpl(WareHouseRepository wareHouseRepository, WareHouseMapper wareHouseMapper, WareHouseSpecification wareHouseSpecification,
			TalukRepository talukRepository) {
		super(wareHouseRepository, wareHouseMapper, wareHouseSpecification);
		this.wareHouseRepository = wareHouseRepository;
		this.wareHouseMapper = wareHouseMapper;
		this.wareHouseSpecification = wareHouseSpecification;
		this.talukRepository = talukRepository;
	}

	@Override
	protected WareHouse beforeSave(WareHouse model) {
		return model;
	}

	@Override
	protected WareHouse beforeUpdate(WareHouseDTO wareHouseDTO, WareHouse model) {
		return talukRepository
				.findById(wareHouseDTO.getTaluk().getId())
				.map(taluk -> {
					model.setTaluk(taluk);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	protected WareHouse afterSave(WareHouseDTO wareHouseDTO, WareHouse model) {
		return model;
	}

	@Override
	protected WareHouse afterUpdate(WareHouseDTO wareHouseDTO, WareHouse model) {
		return model;
	}

	@Override
	protected Specification<WareHouse> getSpecification(FilterCriteria filter, Specification<WareHouse> specification) {
		return specification;
	}

}