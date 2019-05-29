/**
 * 
 */
package com.kirana.avatar.master.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.master.dto.LanguageDTO;
import com.kirana.avatar.master.resource.LanguageResource;
import com.kirana.avatar.master.service.LanguageService;
import com.kirana.avatar.common.controllers.BaseController;

/**
 * @author __Telmila__
 *
 */

@RestController
public class LanguageController extends BaseController<LanguageService, LanguageDTO> implements LanguageResource{

	public LanguageController(LanguageService languageService) {
		super(languageService);
	}

}
