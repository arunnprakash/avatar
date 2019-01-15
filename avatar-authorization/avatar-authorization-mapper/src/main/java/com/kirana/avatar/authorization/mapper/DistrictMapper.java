/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.DistrictDTO;
import com.kirana.avatar.authorization.model.District;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface DistrictMapper extends BaseMapper<DistrictDTO, District>{

}
