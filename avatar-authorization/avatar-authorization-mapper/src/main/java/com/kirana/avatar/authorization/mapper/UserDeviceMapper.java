/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.authorization.dto.UserDeviceDTO;
import com.kirana.avatar.authorization.model.UserDevice;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface UserDeviceMapper extends BaseMapper<UserDeviceDTO, UserDevice>{
	@Mappings({
		@Mapping(target = "user.preferredLanguage", ignore = true),
		@Mapping(target = "user.village", ignore = true),
		@Mapping(target = "user.gender", ignore = true) 
	})
	public UserDeviceDTO toDTO(UserDevice model);
	 
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "user.preferredLanguage", ignore = true),
		@Mapping(target = "user.village", ignore = true),
		@Mapping(target = "user.gender", ignore = true) 
	})
	public UserDevice toModel(UserDeviceDTO dto);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "user.preferredLanguage", ignore = true),
		@Mapping(target = "user.village", ignore = true),
		@Mapping(target = "user.gender", ignore = true) 
	})
	public UserDevice updateModel(UserDeviceDTO dto, @MappingTarget UserDevice model);
}
