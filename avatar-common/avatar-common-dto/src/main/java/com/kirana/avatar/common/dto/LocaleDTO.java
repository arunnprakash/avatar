/*******************************************************************************
 *
 * Copyright (c) 2019 Granatech Limited
 *
 * All information contained herein is, and remains the property of Granatech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Granatech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from 
 * Granatech Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.dto;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@ToString
public class LocaleDTO extends BaseDTO {
	@ApiModelProperty(notes = "English Value of Field")
	protected String en;
	@ApiModelProperty(notes = "Tamil Value of Field")
	protected String ta;
	@ApiModelProperty(notes = "Malayalam Value of Field")
	protected String ma;
	@ApiModelProperty(notes = "Kannadam Value of Field")
	protected String ka;
	@ApiModelProperty(notes = "Telugu Value of Field")
	protected String te;
	public LocaleDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			String en, String ta, String ma, String ka, String te) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.en = en;
		this.ta = ta;
		this.ma = ma;
		this.ka = ka;
		this.te = te;
	}
}
