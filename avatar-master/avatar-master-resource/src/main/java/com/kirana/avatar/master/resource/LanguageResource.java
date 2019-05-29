/**
 * 
 */
package com.kirana.avatar.master.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.master.dto.LanguageDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */

@TypeScriptEndpoint("LanguageService")
@RequestMapping(value= {"/api/language"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface LanguageResource extends BaseResource<LanguageDTO> {

}
