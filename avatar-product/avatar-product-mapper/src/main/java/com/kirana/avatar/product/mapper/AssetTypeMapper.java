/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.product.dto.AssetTypeDTO;
import com.kirana.avatar.product.model.AssetType;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface AssetTypeMapper extends BaseMapper<AssetTypeDTO, AssetType>{

}
