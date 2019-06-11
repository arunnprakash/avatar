/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.transaction.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kirana.avatar.common.jpa.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/**
 * @author __ArunPrakash__
 *
 */

@Entity
@Table(name = "seller_transactions")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class SellerTransaction extends BaseEntity<SellerTransaction> {

	@ManyToOne
	protected SellerTransactionStatus transactionStatus;

	@Column(nullable = false)
	protected Long product;

	@Column(nullable = false)
	protected Long sellerProductQuality;

	@Column(nullable = false)
	protected BigDecimal sellerProductQuantity;

	@Column(nullable = false)
	protected Long seller;
	
	@Column(nullable = true)
	protected Long sellerAgentProductQuality;

	@Column(nullable = true)
	protected BigDecimal sellerAgentProductQuantity;
	
	@Column(nullable = false)
	protected Long sellerAgent;
	
	@Column(nullable = false)
	protected Long truckDriver;
	
	@Column(nullable = false)
	protected Long wareHouse;

	@Column(nullable = true)
	protected BigDecimal wareHouseProductQuantity;
	
	@Column(nullable = true)
	protected Long quantityCheckedBy;

	@Column(nullable = true)
	protected Long wareHouseProductQuality;
	
	@Column(nullable = true)
	protected Long qualityCheckedBy;
}
