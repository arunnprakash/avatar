/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.product.dto.AssetDTO;
import com.kirana.avatar.product.model.Asset;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface AssetMapper extends BaseMapper<AssetDTO, Asset>{

}
