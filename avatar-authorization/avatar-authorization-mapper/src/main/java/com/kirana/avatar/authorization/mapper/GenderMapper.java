/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.GenderDTO;
import com.kirana.avatar.authorization.model.Gender;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface GenderMapper extends BaseMapper<GenderDTO, Gender>{

}
