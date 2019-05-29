/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.AssetDTO;
import com.kirana.avatar.master.model.Asset;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface AssetMapper extends BaseMapper<AssetDTO, Asset>{

}
