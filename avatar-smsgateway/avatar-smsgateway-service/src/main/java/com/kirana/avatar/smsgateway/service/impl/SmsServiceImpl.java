/**
 * 
 */
package com.kirana.avatar.smsgateway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kirana.avatar.smsgateway.dto.ReceivedSmsDTO;
import com.kirana.avatar.smsgateway.dto.SendSmsDTO;
import com.kirana.avatar.smsgateway.service.SmsService;
/**
 * @author __Telmila__
 *
 */
@Service
public class SmsServiceImpl implements SmsService {

	private List<SendSmsDTO> sendSmsDtoList = new ArrayList<>();

	@Value("${received-sms-push-url}")
	private String receivedSmsPushUrl;

	/* (non-Javadoc)
	 * @see com.kirana.avatar.smsgateway.service.SmsService#send(com.kirana.avatar.smsgateway.dto.SendSmsDTO)
	 */
	@Override
	public Boolean send(SendSmsDTO sendSmsDTO) {
		return sendSmsDtoList.add(sendSmsDTO);
	}

	/* (non-Javadoc)
	 * @see com.kirana.avatar.smsgateway.service.SmsService#receiveSms(com.kirana.avatar.smsgateway.dto.ReceivedSmsDTO)
	 */
	@Override
	public Boolean receiveSms(ReceivedSmsDTO receivedSmsDTO) {
		return WebClient.create()
				.get()
				.uri(receivedSmsPushUrl, receivedSmsDTO)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToMono(Boolean.class)
				.block();
	}

	@Override
	public List<SendSmsDTO> getSendSmsList() {
		return sendSmsDtoList;
	}

	@Override
	public Boolean sendSmsCompleted(List<SendSmsDTO> sendSmsDtoList) {
		sendSmsDtoList.forEach(sendSmsDto -> sendSmsDtoList.remove(sendSmsDto));
		return true;
	}

}
