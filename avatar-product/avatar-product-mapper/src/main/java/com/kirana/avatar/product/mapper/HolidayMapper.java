/**
 * 
 */
package com.kirana.avatar.product.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.product.dto.HolidayDTO;
import com.kirana.avatar.product.model.Holiday;

/**
 * @author __Telmila__
 *
 */
@Mapper(componentModel="spring")
public interface HolidayMapper extends BaseMapper<HolidayDTO, Holiday>{

}
