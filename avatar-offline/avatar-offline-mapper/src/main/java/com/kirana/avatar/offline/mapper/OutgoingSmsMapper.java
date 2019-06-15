/**
 * 
 */
package com.kirana.avatar.offline.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.offline.dto.OutgoingSmsDTO;
import com.kirana.avatar.offline.model.OutgoingSms;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface OutgoingSmsMapper extends BaseMapper<OutgoingSmsDTO, OutgoingSms>{

}
