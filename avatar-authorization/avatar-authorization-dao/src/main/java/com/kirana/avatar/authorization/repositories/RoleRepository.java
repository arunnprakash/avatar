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
package com.kirana.avatar.authorization.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __ArunPrakash__
 *
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {

	public List<Role> findByRoleNameEqualsAllIgnoreCase(String roleName, Pageable pageRequest);
	public List<Role> findByRoleNameStartingWithAllIgnoreCase(String roleName, Pageable pageRequest);
	public List<Role> findByRoleNameContainsAllIgnoreCase(String roleName, Pageable pageRequest);
	public List<Role> findByRoleNameContainsAllIgnoreCaseAndDeleted(String filterByItemValue, boolean deleted, Pageable pageRequest);
	public List<Role> findByRoleNameEndingWithAllIgnoreCase(String roleName, Pageable pageRequest);
	
	public Long countByRoleNameEqualsAllIgnoreCase(String roleName);
	public Long countByRoleNameStartingWithAllIgnoreCase(String roleName);
	public Long countByRoleNameContainsAllIgnoreCase(String roleName);
	public Long countByRoleNameContainsAllIgnoreCaseAndDeleted(String filterByItemValue, boolean delete);
	public Long countByRoleNameEndingWithAllIgnoreCase(String roleName);
}
