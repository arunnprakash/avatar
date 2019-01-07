/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.UserDTO;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __ArunPrakash__
 *
 */
@Mapper(componentModel="spring")
public interface UserMapper extends BaseMapper<UserDTO, User>{

}
