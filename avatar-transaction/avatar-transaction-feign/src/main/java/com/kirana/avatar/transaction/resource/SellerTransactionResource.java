package com.kirana.avatar.transaction.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.common.resource.BaseResource;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("SellerTransactionService")
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api/seller-transaction"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface SellerTransactionResource extends BaseResource<SellerTransactionDTO> {

}
