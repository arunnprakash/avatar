/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.UserDeviceDTO;
import com.kirana.avatar.authorization.model.UserDevice;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface UserDeviceMapper extends BaseMapper<UserDeviceDTO, UserDevice>{

}
