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
package com.kirana.avatar.authorization.feign;


import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.authorization.resource.RolesResource;

/**
 * @author __ArunPrakash__
 *
 */
@FeignClient(name="role-service")
public interface RoleClient extends RolesResource {

}
