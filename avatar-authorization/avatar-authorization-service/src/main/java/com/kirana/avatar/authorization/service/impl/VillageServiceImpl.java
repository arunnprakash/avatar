/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;

import static com.kirana.avatar.authorization.model.Village_.VILLAGE_CODE;
import static com.kirana.avatar.authorization.model.Village_.VILLAGE_NAME;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.VillageDTO;
import com.kirana.avatar.authorization.mapper.VillageMapper;
import com.kirana.avatar.authorization.model.Village;
import com.kirana.avatar.authorization.repositories.VillageRepository;
import com.kirana.avatar.authorization.service.VillageService;
import com.kirana.avatar.authorization.specifications.VillageSpecification;
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
public class VillageServiceImpl extends BaseServiceImpl<Village, VillageDTO, VillageMapper, VillageRepository, VillageSpecification> implements VillageService{
	
	private VillageRepository villageRepository;
	private VillageMapper villageMapper;
	private VillageSpecification villageSpecification;
	
	public VillageServiceImpl(VillageRepository villageRepository, VillageMapper villageMapper, VillageSpecification villageSpecification) {
		super(villageRepository, villageMapper, villageSpecification);
		this.villageRepository = villageRepository;
		this.villageMapper = villageMapper;
		this.villageSpecification = villageSpecification;
	}
	
	@Override
	protected Specification<Village> getSpecification(FilterCriteria filter, Specification<Village> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(VILLAGE_NAME)) {
			Specification<Village> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? villageSpecification.hasVillageName(itemValue) : spec.or(villageSpecification.hasVillageName(itemValue));
			}
			log.debug("Adding specification {} {}", VILLAGE_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(VILLAGE_CODE)) {
			Specification<Village> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? villageSpecification.hasVillageCode(itemValue) : spec.or(villageSpecification.hasVillageCode(itemValue));
			}
			log.debug("Adding specification {} {}", VILLAGE_CODE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
