/**
 * 
 */
package com.kirana.avatar.product.dto;

import java.time.ZonedDateTime;

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
public class PriceHistoryDTO extends BaseDTO{
	
	protected ProductDTO product;
	protected Double price;
	protected QualityDTO quality;
	
	@Builder
	public PriceHistoryDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			ProductDTO product, Double price, QualityDTO quality) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.product = product;
		this.price = price;
		this.quality = quality;
	}

}
