/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.model.UserRole;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __ArunPrakash__
 *
 */
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole> {
	public Optional<UserRole> findByUserAndRole(User user, Role role);
}
