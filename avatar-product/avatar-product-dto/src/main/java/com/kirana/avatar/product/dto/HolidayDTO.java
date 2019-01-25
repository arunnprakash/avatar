/**
 * 
 */
package com.kirana.avatar.product.dto;

import java.time.ZonedDateTime;

import com.kirana.avatar.authorization.dto.DistrictDTO;
import com.kirana.avatar.authorization.dto.StateDTO;
import com.kirana.avatar.authorization.dto.TalukDTO;
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
public class HolidayDTO extends BaseDTO{
	
	protected ZonedDateTime startDate;
	protected ZonedDateTime endDate;
	protected String description;
	protected StateDTO state;
	protected DistrictDTO district;
	protected TalukDTO taluk;
	@Builder
	public HolidayDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy, ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			ZonedDateTime startDate, ZonedDateTime endDate, String description, StateDTO state, DistrictDTO district, TalukDTO taluk) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.state = state;
		this.district = district;
		this.taluk = taluk;
	}

}
