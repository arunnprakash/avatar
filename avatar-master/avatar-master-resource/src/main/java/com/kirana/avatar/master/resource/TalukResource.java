/**
 * 
 */
package com.kirana.avatar.master.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.master.dto.TalukDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */

@TypeScriptEndpoint("TalukService")
@RequestMapping(value= {"/api/taluk"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface TalukResource extends BaseResource<TalukDTO>{

}
