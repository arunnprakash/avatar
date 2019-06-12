/*******************************************************************************
 *
 * Copyright (c) 2018 Granatech Limited
 *
 * All information contained herein is, and remains the property of Granatech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Granatech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from Granatech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.transaction.dto;

import java.math.BigDecimal;
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
public class SellerTransactionDTO extends BaseDTO {
	protected String transactionId;
	protected SellerTransactionStatusDTO transactionStatus;
	protected Long product;
	protected Long sellerProductQuality;
	protected BigDecimal sellerProductQuantity;
	protected Long seller;
	protected Long sellerAgentProductQuality;
	protected BigDecimal sellerAgentProductQuantity;
	protected Long sellerAgent;
	protected Long truckDriver;
	protected Long wareHouse;
	protected BigDecimal wareHouseProductQuantity;
	protected Long quantityCheckedBy;
	protected Long wareHouseProductQuality;
	protected Long qualityCheckedBy;
	
	@Builder
	public SellerTransactionDTO(Long id, String createdBy, ZonedDateTime createdDate, String lastModifiedBy,
			ZonedDateTime lastModifiedDate, Boolean deleted, Long version, 
			String transactionId, SellerTransactionStatusDTO transactionStatus, Long product, Long sellerProductQuality, BigDecimal sellerProductQuantity, Long seller,
			Long sellerAgentProductQuality, BigDecimal sellerAgentProductQuantity, Long sellerAgent, Long truckDriver, Long wareHouse, 
			BigDecimal wareHouseProductQuantity, Long quantityCheckedBy, Long wareHouseProductQuality, Long qualityCheckedBy) {
		super(id, createdBy, createdDate, lastModifiedBy, lastModifiedDate, deleted, version);
		this.transactionId = transactionId;
		this.transactionStatus = transactionStatus;
		this.product = product;
		this.sellerProductQuality = sellerProductQuality;
		this.sellerProductQuantity = sellerProductQuantity;
		this.seller = seller;
		this.sellerAgentProductQuality = sellerAgentProductQuality;
		this.sellerAgentProductQuantity = sellerAgentProductQuantity;
		this.sellerAgent = sellerAgent;
		this.truckDriver = truckDriver;
		this.wareHouse = wareHouse;
		this.wareHouseProductQuantity = wareHouseProductQuantity;
		this.quantityCheckedBy = quantityCheckedBy;
		this.wareHouseProductQuality = wareHouseProductQuality;
		this.qualityCheckedBy = qualityCheckedBy;
	}
}
