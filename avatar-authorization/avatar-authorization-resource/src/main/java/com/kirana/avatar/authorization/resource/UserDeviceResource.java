/**
 * 
 */
package com.kirana.avatar.authorization.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.authorization.dto.UserDeviceDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */

@TypeScriptEndpoint("UserDeviceService")
@TypeScriptTemplatesConfiguration(useSuffixes=false)
@RequestMapping(value= {"/api/userDevice"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface UserDeviceResource extends BaseResource<UserDeviceDTO>{

}
