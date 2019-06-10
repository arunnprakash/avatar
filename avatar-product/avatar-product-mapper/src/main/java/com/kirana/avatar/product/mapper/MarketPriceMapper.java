/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.MarketPriceDTO;
import com.kirana.avatar.product.model.MarketPrice;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface MarketPriceMapper extends BaseMapper<MarketPriceDTO, MarketPrice>{

	@Mappings({
		@Mapping(target = "market", ignore = true),
	})
	public MarketPriceDTO toDTO(MarketPrice model);

	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "market", ignore = true),
	})
	public MarketPrice toModel(MarketPriceDTO dto);
	 

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "market", ignore = true),
	})
	public MarketPrice updateModel(MarketPriceDTO dto, @MappingTarget MarketPrice model);
}
