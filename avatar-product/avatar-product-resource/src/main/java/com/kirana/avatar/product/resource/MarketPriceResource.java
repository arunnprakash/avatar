/**
 * 
 */
package com.kirana.avatar.product.resource;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.product.dto.MarketPriceDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("MarketPriceService")
@RequestMapping(value= {"/api/market-price"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface MarketPriceResource extends BaseResource<MarketPriceDTO> {

}
