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
public class WareHouseDTO extends LocaleDTO {

	protected String latitude;
	protected String longitude;
	protected String address;
	protected TalukDTO taluk;
	protected MarketDTO market;
	@Builder
	public WareHouseDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			String en, String ta, String ma, String ka, String te, 
			String latitude, String longitude, String address, TalukDTO taluk, MarketDTO market) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version, 
				en, ta, ma, ka, te);
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.taluk = taluk;
		this.market = market;
	}

}
