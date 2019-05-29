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
public class TruckDriverWareHouseMappingDTO extends BaseDTO {

	protected UserDTO truckDriver;
	protected Map<String, Object> wareHouse;

	@Builder
	public TruckDriverWareHouseMappingDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			UserDTO truckDriver, Map<String, Object> wareHouse) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.truckDriver = truckDriver;
		this.wareHouse = wareHouse;
	}

}
