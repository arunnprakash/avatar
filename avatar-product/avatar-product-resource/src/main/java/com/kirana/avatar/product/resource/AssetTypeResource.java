package com.kirana.avatar.product.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.product.dto.AssetTypeDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("AssetTypeService")
@RequestMapping(value= {"/api/product/asset-type"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface AssetTypeResource extends BaseResource<AssetTypeDTO> {

}
