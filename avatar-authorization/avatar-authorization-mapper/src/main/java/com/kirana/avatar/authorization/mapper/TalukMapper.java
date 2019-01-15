/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.TalukDTO;
import com.kirana.avatar.authorization.model.Taluk;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface TalukMapper extends BaseMapper<TalukDTO, Taluk>{

}
