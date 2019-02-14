/**
 * 
 */
package com.kirana.avatar.product.dto;

import java.time.ZonedDateTime;
import java.util.List;

import com.kirana.avatar.product.dto.AssetDTO;
import com.kirana.avatar.common.dto.LocaleDTO;

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
public class ProductDTO extends LocaleDTO {
	
	protected String productCode;
	protected List<AssetDTO> assets;
	@Builder
	public ProductDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			String en, String ta, String ma, String ka, String te, 
			String productCode,
			List<AssetDTO> assets) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version,
				en, ta, ma, ka, te);
		this.productCode = productCode;
		this.assets = assets;
	}

}
