/**
 * 
 */
package com.kirana.avatar.master.service.impl;

import static com.kirana.avatar.master.model.State_.STATE_CODE;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.DistrictDTO;
import com.kirana.avatar.master.mapper.DistrictMapper;
import com.kirana.avatar.master.model.District;
import com.kirana.avatar.master.repositories.DistrictRepository;
import com.kirana.avatar.master.repositories.StateRepository;
import com.kirana.avatar.master.service.DistrictService;
import com.kirana.avatar.master.specifications.DistrictSpecification;
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
public class DistrictServiceImpl extends BaseServiceImpl<District, DistrictDTO, DistrictMapper, DistrictRepository, DistrictSpecification> implements DistrictService{

	private DistrictRepository districtRepository;
	private DistrictMapper districtMapper;
	private DistrictSpecification districtSpecification;
	private StateRepository stateRepository;
	public DistrictServiceImpl(DistrictRepository districtRepository, DistrictMapper districtMapper, DistrictSpecification districtSpecification,
			StateRepository stateRepository) {
		super(districtRepository, districtMapper, districtSpecification);
		this.districtRepository = districtRepository;
		this.districtMapper = districtMapper;
		this.districtSpecification = districtSpecification;
		this.stateRepository = stateRepository;
	}

	@Override
	protected District beforeSave(DistrictDTO districtDTO, District model) {
		return model;
	}

	@Override
	protected District beforeUpdate(DistrictDTO districtDTO, District model) {
		return stateRepository
				.findById(districtDTO.getState().getId())
				.map(state -> {
					model.setState(state);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
	}


	@Override
	protected District afterSave(DistrictDTO districtDTO, District model) {
		return model;
	}

	@Override
	protected District afterUpdate(DistrictDTO districtDTO, District model) {
		return model;
	}

	@Override
	protected Specification<District> getSpecification(FilterCriteria filter, Specification<District> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(STATE_CODE)) {
			Specification<District> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? districtSpecification.hasDistrictCode(itemValue) : spec.or(districtSpecification.hasDistrictCode(itemValue));
			}
			log.debug("Adding specification {} {}", STATE_CODE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}
	
}
