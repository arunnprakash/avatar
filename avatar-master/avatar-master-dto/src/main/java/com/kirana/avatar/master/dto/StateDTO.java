/**
 * 
 */
package com.kirana.avatar.master.dto;

import java.time.ZonedDateTime;

import com.kirana.avatar.common.dto.BaseDTO;
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
public class StateDTO extends LocaleDTO {

	protected String stateCode;
	private CountryDTO country;
	@Builder
	public StateDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			String en, String ta, String ma, String ka, String te, 
			String stateCode, CountryDTO country) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version,
				en, ta, ma, ka, te);
		this.stateCode = stateCode;
		this.country = country;
	}
	
}
