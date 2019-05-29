/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.VillageDTO;
import com.kirana.avatar.master.model.Village;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface VillageMapper extends BaseMapper<VillageDTO, Village>{

}
