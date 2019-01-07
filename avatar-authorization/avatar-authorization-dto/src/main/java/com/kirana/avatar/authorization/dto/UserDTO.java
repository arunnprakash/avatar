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
package com.kirana.avatar.authorization.dto;

import java.time.ZonedDateTime;
import java.util.List;

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
public class UserDTO extends BaseDTO {
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String mobileNumber;
	protected ZonedDateTime dob;
	protected Boolean suspended;
	protected String latitude;
	protected String longitude;
	protected List<RoleDTO> roles;
	@Builder
	public UserDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, String userName, String firstName, 
			String lastName, String mobileNumber, ZonedDateTime dob, Boolean suspended, String latitude, String longitude, List<RoleDTO> roles) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.dob = dob;
		this.suspended = suspended;
		this.latitude = latitude;
		this.longitude = longitude;
		this.roles = roles;
	}
}
