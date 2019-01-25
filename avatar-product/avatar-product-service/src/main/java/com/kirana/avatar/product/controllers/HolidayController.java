/**
 * 
 */
package com.kirana.avatar.product.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.common.controllers.BaseController;
import com.kirana.avatar.product.dto.HolidayDTO;
import com.kirana.avatar.product.resource.HolidayResource;
import com.kirana.avatar.product.service.HolidayService;

/**
 * @author __Telmila__
 *
 */
@RestController
public class HolidayController extends BaseController<HolidayService, HolidayDTO> implements HolidayResource{

		
	private HolidayService holidayService;

	public HolidayController(HolidayService holidayService) {
		super(holidayService);
		this.holidayService	= holidayService;
	}

}
