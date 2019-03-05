package com.kirana.avatar.transaction.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.leandreck.endpoints.annotations.TypeScriptEndpoint;
import org.leandreck.endpoints.annotations.TypeScriptTemplatesConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirana.avatar.transaction.dto.SellerOrder;
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

	@GetMapping(value= {"/orders-for-seller-agent/{sellerAgentId}/{orderCreatedDate}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<SellerOrder> getOrdersForSellerAgent(@PathVariable("sellerAgentId")Long sellerAgentId, @PathVariable("orderCreatedDate") String orderCreatedDate);

	@GetMapping(value= {"/orders-for-warehouse/{wareHouseId}/{orderCreatedDate}"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<SellerOrder> getOrdersForWareHouse(@PathVariable("wareHouseId")Long wareHouseId, @PathVariable("orderCreatedDate") String orderCreatedDate);
}
