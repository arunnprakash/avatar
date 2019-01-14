/**
 * 
 */
package com.kirana.avatar.authorization.dto;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import com.kirana.avatar.authorization.dto.UserDTO.UserDTOBuilder;
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
public class LanguageDTO extends BaseDTO{
	
	protected String languageCode;
	protected String languageName;
	
	@Builder
	public LanguageDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, String languageCode, String languageName) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.languageCode = languageCode;
		this.languageName = languageName;
	}

}
