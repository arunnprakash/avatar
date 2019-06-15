/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.authorization.dto.SellerAgentWareHouseMappingDTO;
import com.kirana.avatar.authorization.model.SellerAgentWareHouseMapping;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface SellerAgentWareHouseMapper extends BaseMapper<SellerAgentWareHouseMappingDTO, SellerAgentWareHouseMapping> {

	
	@Mappings({
		@Mapping(target = "wareHouse", ignore = true),
		@Mapping(target = "sellerAgent.preferredLanguage", ignore = true),
		@Mapping(target = "sellerAgent.village", ignore = true),
		@Mapping(target = "sellerAgent.gender", ignore = true) 
	})
	public SellerAgentWareHouseMappingDTO toDTO(SellerAgentWareHouseMapping model);

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "wareHouse", ignore = true),
		@Mapping(target = "sellerAgent.preferredLanguage", ignore = true),
		@Mapping(target = "sellerAgent.village", ignore = true),
		@Mapping(target = "sellerAgent.gender", ignore = true) 
	}) 
	public SellerAgentWareHouseMapping toModel(SellerAgentWareHouseMappingDTO dto);


	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "wareHouse", ignore = true),
		@Mapping(target = "sellerAgent.preferredLanguage", ignore = true),
		@Mapping(target = "sellerAgent.village", ignore = true),
		@Mapping(target = "sellerAgent.gender", ignore = true) 
	}) 
	public SellerAgentWareHouseMapping updateModel(SellerAgentWareHouseMappingDTO dto, @MappingTarget SellerAgentWareHouseMapping model);

}
