/*******************************************************************************
 *
 * Copyright (c) 2019 Granatech Limited
 *
 * All information contained herein is, and remains the property of Granatech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Granatech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from 
 * Granatech Limited
 *
 *******************************************************************************/
package com.kirana.avatar.transaction.dto;

import java.util.Map;

/**
 * @author __ArunPrakash__
 *
 */
public class SellerOrder {
	private SellerTransactionDTO sellerTransaction;
	private Map<String, Object> priceTag;
	public SellerTransactionDTO getSellerTransaction() {
		return sellerTransaction;
	}
	public void setSellerTransaction(SellerTransactionDTO sellerTransaction) {
		this.sellerTransaction = sellerTransaction;
	}
	public Map<String, Object> getPriceTag() {
		return priceTag;
	}
	public void setPriceTag(Map<String, Object> priceTag) {
		this.priceTag = priceTag;
	}
}
