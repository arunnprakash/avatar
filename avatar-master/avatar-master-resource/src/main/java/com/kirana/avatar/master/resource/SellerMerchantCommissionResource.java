/**
 * 
 */
package com.kirana.avatar.master.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.master.dto.SellerMerchantCommissionDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */

@TypeScriptEndpoint("SellerMerchantCommissionService")
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api/seller-merchant-commission"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface SellerMerchantCommissionResource extends BaseResource<SellerMerchantCommissionDTO>{

	@RequestMapping(value= {"/latest-seller-merchant-commission"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public SellerMerchantCommissionDTO getLatestSellerMerchantCommission();

}
