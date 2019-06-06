/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.model.SellerPriceHistory;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface SellerPriceHistoryMapper extends BaseMapper<SellerPriceHistoryDTO, SellerPriceHistory>{

}
