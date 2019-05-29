/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.WareHouseDTO;
import com.kirana.avatar.master.model.WareHouse;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface WareHouseMapper extends BaseMapper<WareHouseDTO, WareHouse>{

}
