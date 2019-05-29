/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.authorization.dto.QcWareHouseMappingDTO;
import com.kirana.avatar.authorization.model.QcWareHouseMapping;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel = "spring")
public interface QcWareHouseMapper extends BaseMapper<QcWareHouseMappingDTO, QcWareHouseMapping> {

	@Mappings({
		@Mapping(source = "wareHouse", target = "wareHouse", ignore = true),
		@Mapping(source = "qc.preferredLanguage", target = "qc.preferredLanguage", ignore = true),
		@Mapping(source = "qc.village", target = "qc.village", ignore = true),
		@Mapping(source = "qc.gender", target = "qc.gender", ignore = true) 
	})
	public QcWareHouseMappingDTO toDTO(QcWareHouseMapping model);

	/*
	 * @Mappings({
	 * 
	 * @Mapping(target = "id", ignore = true),
	 * 
	 * @Mapping(target = "createdBy", ignore = true),
	 * 
	 * @Mapping(target = "createdDate", ignore = true),
	 * 
	 * @Mapping(target = "lastModifiedBy", ignore = true),
	 * 
	 * @Mapping(target = "lastModifiedDate", ignore = true),
	 * 
	 * @Mapping(target = "version", ignore = true),
	 * 
	 * @Mapping(target = "deleted", ignore = true),
	 * 
	 * @Mapping(target = "wareHouse", ignore = true),
	 * 
	 * @Mapping(target = "preferredLanguage", ignore = true),
	 * 
	 * @Mapping(target = "village", ignore = true),
	 * 
	 * @Mapping(target = "gender", ignore = true) }) public QcWareHouseMapping
	 * toModel(QcWareHouseMappingDTO dto);
	 */

	/*
	 * @Mappings({
	 * 
	 * @Mapping(target = "id", ignore = true),
	 * 
	 * @Mapping(target = "createdBy", ignore = true),
	 * 
	 * @Mapping(target = "createdDate", ignore = true),
	 * 
	 * @Mapping(target = "lastModifiedBy", ignore = true),
	 * 
	 * @Mapping(target = "lastModifiedDate", ignore = true),
	 * 
	 * @Mapping(target = "version", ignore = true),
	 * 
	 * @Mapping(target = "deleted", ignore = true),
	 * 
	 * @Mapping(target = "wareHouse", ignore = true),
	 * 
	 * @Mapping(target = "preferredLanguage", ignore = true),
	 * 
	 * @Mapping(target = "village", ignore = true),
	 * 
	 * @Mapping(target = "gender", ignore = true) }) public QcWareHouseMapping
	 * updateModel(QcWareHouseMappingDTO dto, @MappingTarget QcWareHouseMapping
	 * model);
	 */
}
