/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.CountryDTO;
import com.kirana.avatar.authorization.model.Country;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface CountryMapper extends BaseMapper<CountryDTO, Country>{

}
