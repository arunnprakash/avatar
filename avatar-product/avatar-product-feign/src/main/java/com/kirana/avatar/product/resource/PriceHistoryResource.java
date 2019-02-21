/**
 * 
 */
package com.kirana.avatar.product.resource;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.product.dto.PriceHistoryDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("PriceHistoryService")
@RequestMapping(value= {"/api/priceHistory"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface PriceHistoryResource extends BaseResource<PriceHistoryDTO>{
	@PostMapping(value= {"/{userId}/{talukId}/{districtId}/{stateId}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public PagingAndFilterResponse<BaseDTO> getProductsForUser(@Valid @RequestBody PagingAndFilterRequest pagingAndFilterRequest, @PathVariable("userId")Long userId, @PathVariable("talukId")Long talukId, @PathVariable("districtId")Long districtId, @PathVariable("stateId")Long stateId);
}
