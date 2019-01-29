/**
 * 
 */
package com.kirana.avatar.product.resource;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.product.dto.ProductAssetDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("ProductAssetService")
@RequestMapping(value= {"/api/productAsset"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface ProductAssetResource extends BaseResource<ProductAssetDTO>{

}