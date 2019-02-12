/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.PriceHistoryDTO;
import com.kirana.avatar.product.model.PriceHistory;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface PriceHistoryMapper extends BaseMapper<PriceHistoryDTO, PriceHistory>{

}
