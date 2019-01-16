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
package com.kirana.avatar.common.mapper;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.jpa.entity.BaseEntity;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;

/**
 * @author __ArunPrakash__
 *
 */
public interface BaseMapper<DTO extends BaseDTO, Model extends BaseEntity<Model>> {
	public DTO toDTO(Model model);
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),	
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true)
	})
	public Model toModel(DTO dto);
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),	
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true)
	})
	public Model updateModel(DTO dto, @MappingTarget Model model);
}
