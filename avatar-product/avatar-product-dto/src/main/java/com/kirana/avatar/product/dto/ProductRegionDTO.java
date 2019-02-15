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
public class ProductRegionDTO extends BaseDTO{
	
	protected ProductDTO product;
	protected Long state;
	protected Long district;
	protected Long taluk;
	@Builder
	public ProductRegionDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate, 
			Boolean deleted, Long version, ProductDTO product, Long state, Long district, Long taluk) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.product = product;
		this.state = state;
		this.district = district;
		this.taluk = taluk;
	}

}
