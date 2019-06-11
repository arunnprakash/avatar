/**
 * 
 */
package com.kirana.avatar.product.dto;

import java.time.ZonedDateTime;
import java.util.Map;

import com.kirana.avatar.common.dto.BaseDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author __Telmila__
 *
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@ToString
public class SellerPriceHistoryDTO extends BaseDTO{
	
	protected MarketPriceDTO marketPrice;
	protected ProductDTO product;
	protected Double price;
	protected QualityDTO quality;
	protected Map<String, Object> sellerAgentCommission;
	protected Map<String, Object> sellerTransportationCharge;
	protected Map<String, Object> sellerMerchantCommission;

	@Builder(toBuilder = true)
	public SellerPriceHistoryDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			MarketPriceDTO marketPrice, Map<String, Object> sellerAgentCommission, 
			Map<String, Object> sellerTransportationCharge, Map<String, Object> sellerMerchantCommission,
			ProductDTO product, Double price, QualityDTO quality) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.marketPrice = marketPrice;
		this.sellerAgentCommission = sellerAgentCommission;
		this.sellerTransportationCharge = sellerTransportationCharge;
		this.sellerMerchantCommission = sellerMerchantCommission;
		this.product = product;
		this.price = price;
		this.quality = quality;
	}

}
