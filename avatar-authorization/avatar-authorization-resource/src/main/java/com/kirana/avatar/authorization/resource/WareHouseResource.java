/**
 * 
 */
package com.kirana.avatar.authorization.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.authorization.dto.WareHouseDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */

@TypeScriptEndpoint("WareHouseService")
@RequestMapping(value= {"/api/warehouse"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface WareHouseResource extends BaseResource<WareHouseDTO>{

}
