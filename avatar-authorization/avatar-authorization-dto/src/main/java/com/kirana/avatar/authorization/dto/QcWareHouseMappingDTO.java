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
public class QcWareHouseMappingDTO extends BaseDTO {

	protected UserDTO qc;
	protected Map<String, Object> wareHouse;

	@Builder(toBuilder = true)
	public QcWareHouseMappingDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			UserDTO qc, Map<String, Object> wareHouse) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.qc = qc;
		this.wareHouse = wareHouse;
	}

}
