/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.VillageDTO;
import com.kirana.avatar.authorization.model.Village;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface VillageMapper extends BaseMapper<VillageDTO, Village>{

}
