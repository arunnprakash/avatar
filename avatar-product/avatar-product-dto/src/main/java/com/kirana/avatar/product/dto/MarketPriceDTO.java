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
public class MarketPriceDTO extends BaseDTO{
	
	protected Map<String, Object> market;
	protected ProductDTO product;
	protected Double price;
	protected QualityDTO quality;
	
	@Builder(toBuilder = true)
	public MarketPriceDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			Map<String, Object> market, ProductDTO product, Double price, QualityDTO quality) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.market = market;
		this.product = product;
		this.price = price;
		this.quality = quality;
	}

}
