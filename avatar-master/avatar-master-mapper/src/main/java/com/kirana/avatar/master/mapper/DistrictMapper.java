/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.DistrictDTO;
import com.kirana.avatar.master.model.District;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface DistrictMapper extends BaseMapper<DistrictDTO, District>{

}
