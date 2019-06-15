/**
 * 
 */
package com.kirana.avatar.notification.service;

import com.kirana.avatar.notification.model.Notification;

/**
 * @author __ArunPrakash__
 *
 */
public interface NotificationSender {
	public void send(Notification notification) throws Exception;
}
