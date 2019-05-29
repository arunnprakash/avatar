/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.TalukDTO;
import com.kirana.avatar.master.model.Taluk;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface TalukMapper extends BaseMapper<TalukDTO, Taluk>{

}
