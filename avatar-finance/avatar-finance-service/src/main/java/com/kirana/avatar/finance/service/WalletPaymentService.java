/**
 * 
 */
package com.kirana.avatar.finance.service;

/**
 * @author __ArunPrakash__
 *
 */
public interface WalletPaymentService {
	public void transferAmount(Long walletId, Double amount);
	public void onTransactionFailed(String transactionReferenceNumber);
}
