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
package com.kirana.avatar.common.service;

import java.util.Collection;
import java.util.List;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;

/**
 * @author __ArunPrakash__
 *
 */
public interface BaseService<DTO extends BaseDTO> {
	public Boolean delete(List<Long> resourceIds);

	public Collection<DTO> getAll(boolean includesDeletedResources);

	public DTO get(Long resourceId);

	public DTO save(DTO resource);

	public DTO update(DTO resource);

	public PagingAndFilterResponse<DTO> getResourceByFilterAndPaging(PagingAndFilterRequest pagingAndFilterRequest, boolean includesDeletedResources);
}
