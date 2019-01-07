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
package com.kirana.avatar.common.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.kirana.avatar.common.jpa.entity.BaseEntity;

/**
 * @author __ArunPrakash__
 *
 */
@NoRepositoryBean
public interface BaseRepository<Model extends BaseEntity<Model>> extends JpaRepository<Model, Long>, JpaSpecificationExecutor<Model> {

	public List<Model> findByDeleted(boolean delete, Pageable pageRequest);
	
	public Long countByDeleted(boolean delete);
}
