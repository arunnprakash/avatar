/**
 * 
 */
package com.kirana.avatar.finance.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.finance.dto.WalletDTO;
import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.finance.model.Wallet;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface WalletMapper extends BaseMapper<WalletDTO, Wallet>{

	@Mappings({
		@Mapping(target = "user", ignore = true)
	})
	public WalletDTO toDTO(Wallet model);

	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "user", ignore = true)
	})
	public Wallet toModel(WalletDTO dto);
	 

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "user", ignore = true)
	})
	public Wallet updateModel(WalletDTO dto, @MappingTarget Wallet model);
}
