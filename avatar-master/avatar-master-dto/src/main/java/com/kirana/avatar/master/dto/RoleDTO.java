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
package com.kirana.avatar.master.dto;

import java.time.ZonedDateTime;

import com.kirana.avatar.common.dto.BaseDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author __ArunPrakash__
 *
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@ToString
public class RoleDTO extends BaseDTO {
	protected String roleName;
	@Builder
	public RoleDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, String roleName) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.roleName = roleName;
	}
}
