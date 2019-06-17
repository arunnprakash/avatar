/**
 * 
 */
package com.kirana.avatar.smsgateway.service;

import java.util.List;

import com.kirana.avatar.smsgateway.dto.ReceivedSmsDTO;
import com.kirana.avatar.smsgateway.dto.SendSmsDTO;

/**
 * @author __Telmila__
 *
 */
public interface SmsService {

	public Boolean send(SendSmsDTO sendSmsDTO);

	public Boolean receiveSms(ReceivedSmsDTO receivedSmsDTO);

	public List<SendSmsDTO> getSendSmsList();

	public Boolean sendSmsCompleted(List<SendSmsDTO> sendSmsDtoList);

}
