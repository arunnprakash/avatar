/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;

import static com.kirana.avatar.authorization.model.UserDevice_.MODEL_NAME;
import static com.kirana.avatar.authorization.model.UserDevice_.MANUFACTURER;
import static com.kirana.avatar.authorization.model.UserDevice_.UUID;
import static com.kirana.avatar.authorization.model.UserDevice_.LOGGED_IN;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kirana.avatar.authorization.dto.UserDeviceDTO;
import com.kirana.avatar.authorization.mapper.UserDeviceMapper;
import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.model.UserDevice;
import com.kirana.avatar.authorization.model.UserRole;
import com.kirana.avatar.authorization.repositories.UserDeviceRepository;
import com.kirana.avatar.authorization.service.UserDeviceService;
import com.kirana.avatar.authorization.specifications.UserDeviceSpecification;
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
@Transactional
public class UserDeviceServiceImpl extends BaseServiceImpl<UserDevice, UserDeviceDTO, UserDeviceMapper, UserDeviceRepository, UserDeviceSpecification> implements UserDeviceService{

	private UserDeviceRepository userDeviceRepository;
	private UserDeviceSpecification userDeviceSpecification;
	private UserDeviceMapper userDeviceMapper;
	
	public UserDeviceServiceImpl(UserDeviceRepository userDeviceRepository, UserDeviceMapper userDeviceMapper,
			UserDeviceSpecification userDeviceSpecification) {
		super(userDeviceRepository, userDeviceMapper, userDeviceSpecification);
		this.userDeviceRepository = userDeviceRepository;
		this.userDeviceMapper = userDeviceMapper;
		this.userDeviceSpecification = userDeviceSpecification;
	}

	@Override
	protected UserDevice beforeSave(UserDeviceDTO userDeviceDTO, UserDevice model) {
		return model;
	}

	@Override
	protected UserDevice afterSave(UserDeviceDTO userDeviceDTO, UserDevice model) {
		return model;
	}

	@Override
	protected UserDevice beforeUpdate(UserDeviceDTO resource, UserDevice model) {
		return model;
	}

	@Override
	protected UserDevice afterUpdate(UserDeviceDTO resource, UserDevice model) {
		return model;
	}

	@Override
	protected Specification<UserDevice> getSpecification(FilterCriteria filter, Specification<UserDevice> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(MODEL_NAME)) {
			Specification<UserDevice> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userDeviceSpecification.hasModelName(itemValue) : spec.or(userDeviceSpecification.hasModelName(itemValue));
			}
			log.debug("Adding specification {} {}", MODEL_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(MANUFACTURER)) {
			Specification<UserDevice> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userDeviceSpecification.hasManufacturer(itemValue) : spec.or(userDeviceSpecification.hasManufacturer(itemValue));
			}
			log.debug("Adding specification {} {}", MANUFACTURER, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(UUID)) {
			Specification<UserDevice> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userDeviceSpecification.hasUuid(itemValue) : spec.or(userDeviceSpecification.hasUuid(itemValue));
			}
			log.debug("Adding specification {} {}", UUID, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(LOGGED_IN)) {
			Specification<UserDevice> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				boolean loggedIn = Boolean.parseBoolean(itemValue);
				spec = (spec == null) ? userDeviceSpecification.hasLoggedIn(loggedIn) : spec.or(userDeviceSpecification.hasLoggedIn(loggedIn));
			}
			log.debug("Adding specification {} {}", LOGGED_IN, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}
	
}
