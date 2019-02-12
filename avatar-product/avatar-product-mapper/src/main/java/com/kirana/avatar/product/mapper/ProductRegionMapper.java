/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.ProductRegionDTO;
import com.kirana.avatar.product.model.ProductRegion;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface ProductRegionMapper extends BaseMapper<ProductRegionDTO, ProductRegion>{

}
