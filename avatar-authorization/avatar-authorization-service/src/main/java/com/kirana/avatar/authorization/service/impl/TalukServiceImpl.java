/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;

import static com.kirana.avatar.authorization.model.State_.STATE_CODE;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.TalukDTO;
import com.kirana.avatar.authorization.mapper.TalukMapper;
import com.kirana.avatar.authorization.model.Taluk;
import com.kirana.avatar.authorization.repositories.DistrictRepository;
import com.kirana.avatar.authorization.repositories.TalukRepository;
import com.kirana.avatar.authorization.service.TalukService;
import com.kirana.avatar.authorization.specifications.TalukSpecification;
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
public class TalukServiceImpl extends BaseServiceImpl<Taluk, TalukDTO, TalukMapper, TalukRepository, TalukSpecification> implements TalukService{

	private TalukRepository talukRepository;
	private TalukMapper talukMapper;
	private TalukSpecification talukSpecification;
	private DistrictRepository districtRepository;

	public TalukServiceImpl(TalukRepository talukRepository, TalukMapper talukMapper, TalukSpecification talukSpecification,
			DistrictRepository districtRepository) {
		super(talukRepository, talukMapper, talukSpecification);
		this.talukRepository = talukRepository;
		this.talukMapper = talukMapper;
		this.talukSpecification = talukSpecification;
		this.districtRepository = districtRepository;
	}

	@Override
	protected Taluk beforeSave(Taluk model) {
		return model;
	}

	@Override
	protected Taluk beforeUpdate(TalukDTO talukDTO, Taluk model) {
		return districtRepository
				.findById(talukDTO.getDistrict().getId())
				.map(district -> {
					model.setDistrict(district);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	protected Taluk afterSave(Taluk model) {
		return model;
	}

	@Override
	protected Taluk afterUpdate(TalukDTO talukDTO, Taluk model) {
		return model;
	}

	@Override
	protected Specification<Taluk> getSpecification(FilterCriteria filter, Specification<Taluk> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(STATE_CODE)) {
			Specification<Taluk> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? talukSpecification.hasTalukCode(itemValue) : spec.or(talukSpecification.hasTalukCode(itemValue));
			}
			log.debug("Adding specification {} {}", STATE_CODE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}
	
}
