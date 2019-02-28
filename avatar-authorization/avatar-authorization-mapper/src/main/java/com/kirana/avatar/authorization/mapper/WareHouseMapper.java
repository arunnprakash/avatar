/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.WareHouseDTO;
import com.kirana.avatar.authorization.model.WareHouse;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface WareHouseMapper extends BaseMapper<WareHouseDTO, WareHouse>{

}
