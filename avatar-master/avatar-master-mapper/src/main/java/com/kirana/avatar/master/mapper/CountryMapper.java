/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.CountryDTO;
import com.kirana.avatar.master.model.Country;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface CountryMapper extends BaseMapper<CountryDTO, Country>{

}
