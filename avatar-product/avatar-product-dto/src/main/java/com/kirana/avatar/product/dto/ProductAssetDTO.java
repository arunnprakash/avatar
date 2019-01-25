/**
 * 
 */
package com.kirana.avatar.product.dto;

import java.time.ZonedDateTime;

import com.kirana.avatar.authorization.dto.AssetDTO;
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
public class ProductAssetDTO extends BaseDTO{
	
	protected ProductDTO productId;
	protected AssetDTO assetId;
	@Builder
	public ProductAssetDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, ProductDTO productId, AssetDTO assetId) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.productId = productId;
		this.assetId = assetId;
	}

}
