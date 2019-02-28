/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.TruckDriverWareHouseMappingDTO;
import com.kirana.avatar.authorization.model.TruckDriverWareHouseMapping;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface TruckDriverWareHouseMapper extends BaseMapper<TruckDriverWareHouseMappingDTO, TruckDriverWareHouseMapping> {

}
