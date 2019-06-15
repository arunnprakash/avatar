package com.kirana.avatar.offline.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.offline.dto.OutgoingSmsDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("OutgoingSmsService")
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api/offline"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface OutgoingSmsResource extends BaseResource<OutgoingSmsDTO> {
}
