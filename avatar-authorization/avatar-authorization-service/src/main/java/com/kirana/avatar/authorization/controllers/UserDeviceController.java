/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.UserDeviceDTO;
import com.kirana.avatar.authorization.resource.UserDeviceResource;
import com.kirana.avatar.authorization.service.UserDeviceService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */
@RestController
public class UserDeviceController extends BaseController<UserDeviceService, UserDeviceDTO> implements UserDeviceResource{
	
	@SuppressWarnings("unused")
	private UserDeviceService userDeviceService;

	public UserDeviceController(UserDeviceService userDeviceService) {
		super(userDeviceService);
		this.userDeviceService = userDeviceService;
	}

}
