/**
 * 
 */
package com.kirana.avatar.authorization.dto;

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
public class UserDeviceDTO extends BaseDTO{
	
	protected String modelName;
	protected String manufacturer;
	protected String imeiNumber;
	protected boolean loggedIn;
	
	@Builder
	public UserDeviceDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, String modelName, String manufacturer, 
			String imeiNumber, Boolean loggedIn) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.modelName = modelName;
		this.manufacturer = manufacturer;
		this.imeiNumber = imeiNumber;
		this.loggedIn = loggedIn;
	}

}
