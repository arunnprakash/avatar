/**
 * 
 */
package com.kirana.avatar.offline.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.offline.dto.IncomingSmsDTO;
import com.kirana.avatar.offline.model.IncomingSms;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface IncomingSmsMapper extends BaseMapper<IncomingSmsDTO, IncomingSms>{

}
