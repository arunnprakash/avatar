/**
 * 
 */
package com.kirana.avatar.authorization.dto;

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
public class SellerAgentWareHouseMappingDTO extends BaseDTO {

	protected UserDTO sellerAgent;
	protected Map<String, Object> wareHouse;

	@Builder(toBuilder = true)
	public SellerAgentWareHouseMappingDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			UserDTO sellerAgent, Map<String, Object> wareHouse) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.sellerAgent = sellerAgent;
		this.wareHouse = wareHouse;
	}

}
