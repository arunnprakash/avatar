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
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __ArunPrakash__
 *
 */
@Mapper(componentModel="spring")
public interface UserMapper extends BaseMapper<UserDTO, User> {

	@Mappings({
		@Mapping(target = "preferredLanguage", ignore = true),
		@Mapping(target = "village", ignore = true),
		@Mapping(target = "gender", ignore = true) 
	})
	public UserDTO toDTO(User model);
	 
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "preferredLanguage", ignore = true),
		@Mapping(target = "village", ignore = true),
		@Mapping(target = "gender", ignore = true) 
	})
	public User toModel(UserDTO dto);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "preferredLanguage", ignore = true),
		@Mapping(target = "village", ignore = true),
		@Mapping(target = "gender", ignore = true) 
	})
	public User updateModel(UserDTO dto, @MappingTarget User model);

}
