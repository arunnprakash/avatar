/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.ProductAssetDTO;
import com.kirana.avatar.product.model.ProductAsset;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface ProductAssetMapper extends BaseMapper<ProductAssetDTO, ProductAsset>{

}
