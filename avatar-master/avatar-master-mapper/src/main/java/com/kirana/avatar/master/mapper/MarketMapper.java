/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.MarketDTO;
import com.kirana.avatar.master.model.Market;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface MarketMapper extends BaseMapper<MarketDTO, Market>{

}
