/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.AssetTypeDTO;
import com.kirana.avatar.authorization.model.AssetType;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface AssetTypeMapper extends BaseMapper<AssetTypeDTO, AssetType>{

}
