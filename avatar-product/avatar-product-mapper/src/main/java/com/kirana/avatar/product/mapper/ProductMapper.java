/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.ProductDTO;
import com.kirana.avatar.product.model.Product;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface ProductMapper extends BaseMapper<ProductDTO, Product>{

}
