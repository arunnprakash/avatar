package com.kirana.avatar.finance.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.finance.dto.BankAccountDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("BankAccountService")
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api/bank-account"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface BankAccountResource extends BaseResource<BankAccountDTO> {
}
