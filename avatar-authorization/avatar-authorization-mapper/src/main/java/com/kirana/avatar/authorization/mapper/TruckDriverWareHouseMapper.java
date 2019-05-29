/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.authorization.dto.TruckDriverWareHouseMappingDTO;
import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface TruckDriverWareHouseMapper extends BaseMapper<TruckDriverWareHouseMappingDTO, TruckDriverWareHouseMapping> {

	
	@Mappings({
		@Mapping(target = "wareHouse", ignore = true),
		@Mapping(target = "truckDriver.preferredLanguage", ignore = true),
		@Mapping(target = "truckDriver.village", ignore = true),
		@Mapping(target = "truckDriver.gender", ignore = true) 
	})
	public TruckDriverWareHouseMappingDTO toDTO(TruckDriverWareHouseMapping model);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "wareHouse", ignore = true),
		@Mapping(target = "truckDriver.preferredLanguage", ignore = true),
		@Mapping(target = "truckDriver.village", ignore = true),
		@Mapping(target = "truckDriver.gender", ignore = true) 
	}) 
	public TruckDriverWareHouseMapping toModel(TruckDriverWareHouseMappingDTO dto);


	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "wareHouse", ignore = true),
		@Mapping(target = "truckDriver.preferredLanguage", ignore = true),
		@Mapping(target = "truckDriver.village", ignore = true),
		@Mapping(target = "truckDriver.gender", ignore = true) 
	}) 
	public TruckDriverWareHouseMapping updateModel(TruckDriverWareHouseMappingDTO dto, @MappingTarget TruckDriverWareHouseMapping model);

}
