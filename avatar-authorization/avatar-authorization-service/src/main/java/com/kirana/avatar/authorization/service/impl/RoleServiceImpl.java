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
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.RoleDTO;
import com.kirana.avatar.authorization.mapper.RoleMapper;
import com.kirana.avatar.authorization.model.Role;
import static com.kirana.avatar.authorization.model.Role_.ROLE_NAME;
import com.kirana.avatar.authorization.model.User_;
import com.kirana.avatar.authorization.repositories.RoleRepository;
import com.kirana.avatar.authorization.service.RoleService;
import com.kirana.avatar.authorization.specifications.RoleSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDTO, RoleMapper, RoleRepository, RoleSpecification> implements RoleService {

	private RoleRepository roleRepository;
	private RoleMapper roleMapper;
	private RoleSpecification roleSpecification;
	public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, RoleSpecification roleSpecification) {
		super(roleRepository, roleMapper, roleSpecification);
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
		this.roleSpecification = roleSpecification;
	}
	@Override
	protected Specification<Role> getSpecification(FilterCriteria filter, Specification<Role> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(ROLE_NAME)) {
			Specification<Role> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? roleSpecification.hasRoleName(itemValue) : spec.or(roleSpecification.hasRoleName(itemValue));
			}
			log.debug("Adding specification {} {}", ROLE_NAME, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
