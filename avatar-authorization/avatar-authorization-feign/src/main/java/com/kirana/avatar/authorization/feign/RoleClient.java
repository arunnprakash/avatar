/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.feign;


import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.authorization.resource.RolesResource;
import com.kirana.avatar.common.feign.config.FeignClientContract;

/**
 * @author __ArunPrakash__
 *
 */
@FeignClient(name="authorization-service", configuration={FeignClientContract.class})
public interface RoleClient extends RolesResource {

}
