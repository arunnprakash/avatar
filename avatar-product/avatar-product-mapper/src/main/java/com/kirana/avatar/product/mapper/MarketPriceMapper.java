/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.MarketPriceDTO;
import com.kirana.avatar.product.model.MarketPrice;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface MarketPriceMapper extends BaseMapper<MarketPriceDTO, MarketPrice>{

}
