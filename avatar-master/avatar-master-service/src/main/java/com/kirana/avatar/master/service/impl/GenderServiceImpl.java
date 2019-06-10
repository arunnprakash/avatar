/**
 * 
 */
package com.kirana.avatar.master.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.GenderDTO;
import com.kirana.avatar.master.mapper.GenderMapper;
import com.kirana.avatar.master.model.Gender;
import com.kirana.avatar.master.model.Gender;
import com.kirana.avatar.master.repositories.GenderRepository;
import com.kirana.avatar.master.service.GenderService;
import com.kirana.avatar.master.specifications.GenderSpecification;
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
public class GenderServiceImpl extends BaseServiceImpl<Gender, GenderDTO, GenderMapper, GenderRepository, GenderSpecification> implements GenderService{

	private GenderRepository genderRepository;
	private GenderMapper genderMapper;
	private GenderSpecification genderSpecification;
	public GenderServiceImpl(GenderRepository genderRepository, GenderMapper genderMapper, GenderSpecification genderSpecification) {
		super(genderRepository, genderMapper, genderSpecification);
		this.genderRepository = genderRepository;
		this.genderMapper = genderMapper;
		this.genderSpecification = genderSpecification;
	}

	@Override
	protected Gender beforeSave(GenderDTO genderDTO, Gender model) {
		return model;
	}

	@Override
	protected Gender beforeUpdate(GenderDTO genderDTO, Gender model) {
		return model;
	}

	@Override
	protected Gender afterSave(GenderDTO genderDTO, Gender model) {
		return model;
	}

	@Override
	protected Gender afterUpdate(GenderDTO genderDTO, Gender model) {
		return model;
	}

	@Override
	protected Specification<Gender> getSpecification(FilterCriteria filter, Specification<Gender> specification) {
		// TODO Auto-generated method stub
		return null;
	}

}
