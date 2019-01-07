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
package com.kirana.avatar.common.dto;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class BaseDTO {
	@ApiModelProperty(notes = "The database generated ID")
	protected Long id;

	@ApiModelProperty(notes = "Currently loggin username")
	protected String createdBy;

	@ApiModelProperty(notes = "Created date")
	protected ZonedDateTime createdDate;

	@ApiModelProperty(notes = "Currently loggin username")
	protected String lastModifiedBy;

	@ApiModelProperty(notes = "Modified Date")
	protected ZonedDateTime lastModifiedDate;

	@ApiModelProperty(notes = "Indicate record is deleted")
	protected Boolean deleted;

	@ApiModelProperty(notes = "The auto-generated version of the record")
	protected Long version;
}
