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

	protected UserDTO user;
	protected String modelName;
	protected String modelType;
	protected String os;
	protected String osVersion;
	protected String sdkVersion;
	protected String language;
	protected String manufacturer;
	protected String uuid;
	protected String screenScale;
	protected String screenWidth;
	protected String screenHeight;
	protected boolean loggedIn;

	@Builder
	public UserDeviceDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, UserDTO user, String modelName, 
			String modelType, String os, String osVersion, String sdkVersion, String language, String manufacturer, 
			String uuid, String screenScale, String screenWidth, String screenHeight, Boolean loggedIn) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.user = user;
		this.modelName = modelName;
		this.modelType = modelType;
		this.os = os;
		this.osVersion = osVersion;
		this.sdkVersion = sdkVersion;
		this.language = language;
		this.manufacturer = manufacturer;
		this.uuid = uuid;
		this.screenScale = screenScale;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.loggedIn = loggedIn;
	}

}
