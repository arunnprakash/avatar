/**
 * 
 */
package com.kirana.avatar.finance.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.notification.dto.NotificationDTO;
import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.finance.model.Notification;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface NotificationMapper extends BaseMapper<NotificationDTO, Notification>{

}
