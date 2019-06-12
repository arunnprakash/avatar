/**
 * 
 */
package com.kirana.avatar.notification.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.notification.dto.NotificationDTO;
import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface NotificationMapper extends BaseMapper<NotificationDTO, Notification>{

}
