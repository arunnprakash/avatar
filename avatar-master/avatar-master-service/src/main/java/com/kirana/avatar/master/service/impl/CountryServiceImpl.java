/**
 * 
 */
package com.kirana.avatar.master.service.impl;

import static com.kirana.avatar.master.model.Country_.COUNTRY_CODE;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.CountryDTO;
import com.kirana.avatar.master.mapper.CountryMapper;
import com.kirana.avatar.master.model.Country;
import com.kirana.avatar.master.repositories.CountryRepository;
import com.kirana.avatar.master.service.CountryService;
import com.kirana.avatar.master.specifications.CountrySpecification;
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
public class CountryServiceImpl extends BaseServiceImpl<Country, CountryDTO, CountryMapper, CountryRepository, CountrySpecification> implements CountryService{

	private CountryRepository countryRepository;
	private CountryMapper countryMapper;
	private CountrySpecification countrySpecification;
	
	public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper, CountrySpecification countrySpecification) {
		super(countryRepository, countryMapper, countrySpecification);
		this.countryRepository = countryRepository;
		this.countryMapper = countryMapper;
		this.countrySpecification = countrySpecification;
	}

	@Override
	protected Country beforeSave(Country model) {
		return model;
	}

	@Override
	protected Country beforeUpdate(CountryDTO countryDTO, Country model) {
		return model;
	}

	@Override
	protected Country afterSave(CountryDTO countryDTO, Country model) {
		return model;
	}

	@Override
	protected Country afterUpdate(CountryDTO countryDTO, Country model) {
		return model;
	}

	@Override
	protected Specification<Country> getSpecification(FilterCriteria filter, Specification<Country> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(COUNTRY_CODE)) {
			Specification<Country> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? countrySpecification.hasCountryCode(itemValue) : spec.or(countrySpecification.hasCountryCode(itemValue));
			}
			log.debug("Adding specification {} {}", COUNTRY_CODE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}
	
}
