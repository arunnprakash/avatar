/**
 * 
 */
package com.kirana.avatar.master.dto;

import java.time.ZonedDateTime;

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
public class SellerTransportationChargeDTO extends BaseDTO {

	protected Double transportationCharge;
	protected Double exemption;
	@Builder
	public SellerTransportationChargeDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			Double transportationCharge, Double exemption) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.transportationCharge = transportationCharge;
		this.exemption = exemption;
	}

}
