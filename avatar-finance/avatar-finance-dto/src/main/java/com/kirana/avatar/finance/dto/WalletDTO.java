/*******************************************************************************
 *
 * Copyright (c) 2018 Granatech Limited
 *
 * All information contained herein is, and remains the property of Granatech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Granatech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from Granatech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.finance.dto;

import java.time.ZonedDateTime;

import com.kirana.avatar.common.dto.BaseDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author __Telmila__
 *
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@ToString
public class WalletDTO extends BaseDTO {

	@Builder
	public WalletDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
	}
}
