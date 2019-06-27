/**
 * 
 */
package com.kirana.avatar.finance.service;

import com.kirana.avatar.finance.model.Notification;

/**
 * @author __ArunPrakash__
 *
 */
public interface NotificationSender {
	public void send(Notification notification) throws Exception;
}
