/**
 * 
 */
package com.kirana.avatar.product.resource;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.product.dto.PriceHistoryDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("PriceHistoryService")
@RequestMapping(value= {"/api/priceHistory"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface PriceHistoryResource extends BaseResource<PriceHistoryDTO>{

}
