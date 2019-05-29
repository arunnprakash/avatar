/**
 * 
 */
package com.kirana.avatar.master.dto;

import java.time.ZonedDateTime;

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
public class VillageDTO extends LocaleDTO {

	protected String villageCode;
	protected TalukDTO taluk;
	@Builder
	public VillageDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			String en, String ta, String ma, String ka, String te, 
			String villageCode, TalukDTO taluk) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version,
				en, ta, ma, ka, te);
		this.villageCode = villageCode;
		this.taluk = taluk;
	}
	
}
