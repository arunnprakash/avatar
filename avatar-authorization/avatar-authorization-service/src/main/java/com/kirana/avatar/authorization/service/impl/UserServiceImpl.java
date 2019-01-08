/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.mapper.UserMapper;
import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.authorization.model.User;
import static com.kirana.avatar.authorization.model.User_.USER_NAME;
import static com.kirana.avatar.authorization.model.User_.FIRST_NAME;
import static com.kirana.avatar.authorization.model.User_.LAST_NAME;
import static com.kirana.avatar.authorization.model.User_.SUSPENDED;
import static com.kirana.avatar.authorization.model.User_.MOBILE_NUMBER;
import static com.kirana.avatar.authorization.model.User_.SUSPENDED;
import com.kirana.avatar.authorization.repositories.UserRepository;
import com.kirana.avatar.authorization.service.UserService;
import com.kirana.avatar.authorization.specifications.UserSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.jpa.entity.BaseEntity_;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;
/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, UserDTO, UserMapper, UserRepository, UserSpecification> implements UserService {

	private UserRepository userRepository;
	private UserMapper userMapper;
	private UserSpecification userSpecification;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserSpecification userSpecification) {
		super(userRepository, userMapper, userSpecification);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userSpecification = userSpecification;
	}

	@Override
	protected Specification<User> getSpecification(FilterCriteria filter, Specification<User> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(USER_NAME)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasUserName(itemValue) : spec.or(userSpecification.hasUserName(itemValue));
			}
			log.debug("Adding specification {} {}", USER_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(FIRST_NAME)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasFirstName(itemValue) : spec.or(userSpecification.hasFirstName(itemValue));
			}
			log.debug("Adding specification {} {}", FIRST_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(LAST_NAME)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasLastName(itemValue) : spec.or(userSpecification.hasLastName(itemValue));
			}
			log.debug("Adding specification {} {}", LAST_NAME, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(MOBILE_NUMBER)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? userSpecification.hasMobileNumber(itemValue) : spec.or(userSpecification.hasMobileNumber(itemValue));
			}
			log.debug("Adding specification {} {}", MOBILE_NUMBER, spec);
			specification = specification.and(spec);
		} else if (itemName.equalsIgnoreCase(SUSPENDED)) {
			Specification<User> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				boolean suspended = Boolean.parseBoolean(itemValue);
				spec = (spec == null) ? userSpecification.hasSuspended(suspended) : spec.or(userSpecification.hasSuspended(suspended));
			}
			log.debug("Adding specification {} {}", SUSPENDED, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

	@Override
	public UserDTO findByUserNameOrMobileNumber(String userNameOrMobileNumber) {
		User user = userRepository
				.findByUserNameOrMobileNumber(userNameOrMobileNumber, userNameOrMobileNumber)
				.orElseThrow(() -> 
					new UsernameNotFoundException("User not found with username or mobilenumber : " + userNameOrMobileNumber)
				);
		log.debug("User :: {}", user);
		return userMapper.toDTO(user);
	}

}
