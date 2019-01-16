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

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __ArunPrakash__
 *
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

	public Optional<User> findByUserName(String username);

	public Optional<User> findByUserNameOrMobileNumber(String userName, String mobileNumber);

	public Long countByUserNameOrMobileNumberContainsAllIgnoreCaseAndDeleted(String userName, String mobileNumber, boolean deleted);

	public List<User> findByUserNameOrMobileNumberContainsAllIgnoreCaseAndDeleted(String userName, String mobileNumber,
			boolean deleted, Pageable pageRequest);

}
