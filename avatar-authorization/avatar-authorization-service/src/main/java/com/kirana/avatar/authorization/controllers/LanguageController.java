/**
 * 
 */
package com.kirana.avatar.authorization.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.authorization.dto.LanguageDTO;
import com.kirana.avatar.authorization.resource.LanguageResource;
import com.kirana.avatar.authorization.service.LanguageService;
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
