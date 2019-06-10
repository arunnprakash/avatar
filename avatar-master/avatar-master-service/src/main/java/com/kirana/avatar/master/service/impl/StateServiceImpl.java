/**
 * 
 */
package com.kirana.avatar.master.service.impl;

import static com.kirana.avatar.master.model.State_.STATE_CODE;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.StateDTO;
import com.kirana.avatar.master.mapper.StateMapper;
import com.kirana.avatar.master.model.State;
import com.kirana.avatar.master.repositories.CountryRepository;
import com.kirana.avatar.master.repositories.StateRepository;
import com.kirana.avatar.master.service.StateService;
import com.kirana.avatar.master.specifications.StateSpecification;
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
public class StateServiceImpl extends BaseServiceImpl<State, StateDTO, StateMapper, StateRepository, StateSpecification> implements StateService{

	private StateRepository stateRepository;
	private StateMapper stateMapper;
	private StateSpecification stateSpecification;
	private CountryRepository countryRepository;
	
	public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper, StateSpecification stateSpecification,
			CountryRepository countryRepository) {
		super(stateRepository, stateMapper, stateSpecification);
		this.stateRepository = stateRepository;
		this.stateMapper = stateMapper;
		this.stateSpecification = stateSpecification;
		this.countryRepository = countryRepository;
	}

	@Override
	protected State beforeSave(StateDTO stateDTO, State model) {
		return model;
	}

	@Override
	protected State beforeUpdate(StateDTO stateDTO, State model) {
		return countryRepository
				.findById(stateDTO.getCountry().getId())
				.map(country -> {
					model.setCountry(country);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	protected State afterSave(StateDTO stateDTO, State model) {
		return model;
	}

	@Override
	protected State afterUpdate(StateDTO stateDTO, State model) {
		return model;
	}

	@Override
	protected Specification<State> getSpecification(FilterCriteria filter, Specification<State> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(STATE_CODE)) {
			Specification<State> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? stateSpecification.hasStateCode(itemValue) : spec.or(stateSpecification.hasStateCode(itemValue));
			}
			log.debug("Adding specification {} {}", STATE_CODE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

	@Override
	protected StateDTO afterLoad(StateDTO resource, State model) {
		return resource;
	}
	
}
