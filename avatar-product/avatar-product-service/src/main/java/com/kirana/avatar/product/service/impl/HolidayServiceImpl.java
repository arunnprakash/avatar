/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import static com.kirana.avatar.product.model.Holiday_.DESCRIPTION;
import static com.kirana.avatar.product.model.Holiday_.END_DATE;
import static com.kirana.avatar.product.model.Holiday_.START_DATE;

import java.time.ZonedDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.HolidayDTO;
import com.kirana.avatar.product.mapper.HolidayMapper;
import com.kirana.avatar.product.model.Holiday;
import com.kirana.avatar.product.repositories.HolidayRepository;
import com.kirana.avatar.product.service.HolidayService;
import com.kirana.avatar.product.specifications.HolidaySpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class HolidayServiceImpl extends BaseServiceImpl<Holiday, HolidayDTO, HolidayMapper, HolidayRepository, HolidaySpecification> implements HolidayService{
	
	private HolidayRepository holidayRepository;
	private HolidayMapper holidayMapper;
	private HolidaySpecification holidaySpecification;
	public HolidayServiceImpl(HolidayRepository holidayRepository, HolidayMapper holidayMapper,HolidaySpecification holidaySpecification) {
		super(holidayRepository, holidayMapper, holidaySpecification);
		this.holidayRepository = holidayRepository;
		this.holidayMapper = holidayMapper;
		this.holidaySpecification = holidaySpecification;
	}
	@Override
	protected Holiday beforeSave(HolidayDTO holidayDTO, Holiday model) {
		return model;
	}
	@Override
	protected Holiday afterSave(HolidayDTO holidayDTO, Holiday model) {
		return model;
	}
	@Override
	protected Holiday beforeUpdate(HolidayDTO resource, Holiday model) {
		return model;
	}
	@Override
	protected Holiday afterUpdate(HolidayDTO resource, Holiday model) {
		return model;
	}
	@Override
	protected Specification<Holiday> getSpecification(FilterCriteria filter, Specification<Holiday> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(DESCRIPTION)) {
			Specification<Holiday> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? holidaySpecification.hasDescription(itemValue) : spec.or(holidaySpecification.hasDescription(itemValue));
			}
			log.debug("Adding specification {} {}", DESCRIPTION, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(START_DATE)) {
			Specification<Holiday> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				ZonedDateTime date = ZonedDateTime.parse(itemValue);
				spec = (spec == null) ? holidaySpecification.hasStartDate(date) : spec.or(holidaySpecification.hasStartDate(date));
			}
			log.debug("Adding specification {} {}", START_DATE, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(END_DATE)) {
			Specification<Holiday> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				ZonedDateTime date = ZonedDateTime.parse(itemValue);
				spec = (spec == null) ? holidaySpecification.hasEndDate(date) : spec.or(holidaySpecification.hasEndDate(date));
			}
			log.debug("Adding specification {} {}", END_DATE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}
	@Override
	protected HolidayDTO afterLoad(HolidayDTO resource, Holiday model) {
		return resource;
	}

}
