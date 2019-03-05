/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.QcWareHouseMappingDTO;
import com.kirana.avatar.authorization.model.QcWareHouseMapping;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface QcWareHouseMapper extends BaseMapper<QcWareHouseMappingDTO, QcWareHouseMapping> {

}
