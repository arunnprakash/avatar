/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.AssetTypeDTO;
import com.kirana.avatar.master.model.AssetType;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface AssetTypeMapper extends BaseMapper<AssetTypeDTO, AssetType>{

}
