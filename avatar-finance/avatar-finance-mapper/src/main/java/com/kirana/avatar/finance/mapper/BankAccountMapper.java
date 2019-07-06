/**
 * 
 */
package com.kirana.avatar.finance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.finance.dto.BankAccountDTO;
import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.finance.model.BankAccount;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface BankAccountMapper extends BaseMapper<BankAccountDTO, BankAccount>{

	@Mappings({
		@Mapping(target = "user", ignore = true),
		@Mapping(target = "bank", ignore = true)
	})
	public BankAccountDTO toDTO(BankAccount model);

	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "user", ignore = true),
		@Mapping(target = "bank", ignore = true)
	})
	public BankAccount toModel(BankAccountDTO dto);
	 

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "user", ignore = true),
		@Mapping(target = "bank", ignore = true)
	})
	public BankAccount updateModel(BankAccountDTO dto, @MappingTarget BankAccount model);
}
