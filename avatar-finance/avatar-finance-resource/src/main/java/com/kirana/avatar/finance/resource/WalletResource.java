package com.kirana.avatar.finance.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.common.resource.BaseResource;
import com.kirana.avatar.finance.dto.WalletDTO;

/**
 * @author __Telmila__
 *
 */
@TypeScriptEndpoint("WalletService")
@TypeScriptTemplatesConfiguration(useSuffixes = false)
@RequestMapping(value= {"/api/wallet"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public interface WalletResource extends BaseResource<WalletDTO> {

	@GetMapping(value= {"/{userId}/credit/{amount}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public WalletDTO creditAmount(@PathVariable("userId") Long userId, @PathVariable("amount") Double amount);

	@GetMapping(value= {"/{userId}/transfer-to-bank-account/{amount}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public WalletDTO transferAmountToUserBankAccount(@PathVariable("userId") Long userId, @PathVariable("amount") Double amount);
	
	@GetMapping(value= {"/{userId}/transfer-all-amount-to-bank-account"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public WalletDTO transferAllAmountToUserBankAccount(@PathVariable("userId") Long userId);
}
