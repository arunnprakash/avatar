/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.QualityDTO;
import com.kirana.avatar.product.model.Quality;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface QualityMapper extends BaseMapper<QualityDTO, Quality>{

}
