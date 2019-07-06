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
package com.kirana.avatar.finance.service;

import com.kirana.avatar.common.service.BaseService;
import com.kirana.avatar.finance.dto.WalletDTO;

/**
 * @author __ArunPrakash__
 *
 */
public interface WalletService extends BaseService<WalletDTO>{

	public WalletDTO transferAmountToUserBankAccount(Long userId, Double amount);

	public WalletDTO transferAllAmountToUserBankAccount(Long userId);

	public WalletDTO creditAmount(Long userId, Double amount);
}
