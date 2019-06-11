/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.model.SellerPriceHistory;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface SellerPriceHistoryMapper extends BaseMapper<SellerPriceHistoryDTO, SellerPriceHistory>{

	@Mappings({
		@Mapping(target = "sellerAgentCommission", ignore = true),
		@Mapping(target = "sellerTransportationCharge", ignore = true),
		@Mapping(target = "sellerMerchantCommission", ignore = true),
		@Mapping(source = "marketPrice.market", target = "marketPrice.market", ignore = true)
	})
	public SellerPriceHistoryDTO toDTO(SellerPriceHistory model);

	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "sellerAgentCommission", ignore = true),
		@Mapping(target = "sellerTransportationCharge", ignore = true),
		@Mapping(target = "sellerMerchantCommission", ignore = true),
		@Mapping(source = "marketPrice.market", target = "marketPrice.market", ignore = true)
	})
	public SellerPriceHistory toModel(SellerPriceHistoryDTO dto);
	 

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "createdBy", ignore = true),
		@Mapping(target = "createdDate", ignore = true),
		@Mapping(target = "lastModifiedBy", ignore = true),
		@Mapping(target = "lastModifiedDate", ignore = true),
		@Mapping(target = "version", ignore = true),
		@Mapping(target = "deleted", ignore = true),
		@Mapping(target = "sellerAgentCommission", ignore = true),
		@Mapping(target = "sellerTransportationCharge", ignore = true),
		@Mapping(target = "sellerMerchantCommission", ignore = true),
		@Mapping(source = "marketPrice.market", target = "marketPrice.market", ignore = true)
	})
	public SellerPriceHistory updateModel(SellerPriceHistoryDTO dto, @MappingTarget SellerPriceHistory model);
}
