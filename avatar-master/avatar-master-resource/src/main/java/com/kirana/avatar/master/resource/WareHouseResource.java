/**
 * 
 */
package com.kirana.avatar.master.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.master.dto.WareHouseDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */

@TypeScriptEndpoint("WareHouseService")
@RequestMapping(value= {"/api/warehouse"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface WareHouseResource extends BaseResource<WareHouseDTO>{

	@GetMapping(value= {"/api/find-by-marketid/{marketId}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<WareHouseDTO> getWareHousesByMarket(@PathVariable("marketId") Long marketId);

}
