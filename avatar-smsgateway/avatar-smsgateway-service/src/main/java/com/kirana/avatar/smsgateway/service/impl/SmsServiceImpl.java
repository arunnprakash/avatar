/**
 * 
 */
package com.kirana.avatar.smsgateway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
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

	private static Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	private List<SendSmsDTO> sendSmsDtoList = new ArrayList<>();
	private List<ReceivedSmsDTO> receivedSmsDtoList = new ArrayList<>();
	
	@Value("${received-sms-push-url}")
	private String receivedSmsPushUrl;

	/* (non-Javadoc)
	 * @see com.kirana.avatar.smsgateway.service.SmsService#send(com.kirana.avatar.smsgateway.dto.SendSmsDTO)
	 */
	@Override
	public Boolean send(SendSmsDTO sendSmsDTO) {
		logger.debug("send sms {}", sendSmsDTO);
		return sendSmsDtoList.add(sendSmsDTO);
	}

	/* (non-Javadoc)
	 * @see com.kirana.avatar.smsgateway.service.SmsService#receiveSms(com.kirana.avatar.smsgateway.dto.ReceivedSmsDTO)
	 */
	@Override
	public Boolean receiveSms(ReceivedSmsDTO receivedSmsDTO) {
		logger.debug("Received sms {}", receivedSmsDTO);
		receivedSmsDtoList.add(receivedSmsDTO);
		return true;
	}

	@Override
	public List<SendSmsDTO> getSendSmsList() {
		logger.debug("getting Sms List {}", sendSmsDtoList);
		return sendSmsDtoList;
	}

	@Override
	public Boolean sendSmsCompleted(List<SendSmsDTO> sendSmsDtoList) {
		logger.debug("sendSmsDtoList before remove from list {}", this.sendSmsDtoList);
		sendSmsDtoList.forEach(sendSmsDto -> this.sendSmsDtoList.remove(sendSmsDto));
		logger.debug("sendSmsDtoList after removed from list {}", this.sendSmsDtoList);
		return true;
	}

	@Scheduled(fixedRate = 10000, initialDelay = 30000)
	public void pushReceivedSms() {
		if (!receivedSmsDtoList.isEmpty()) {
			ReceivedSmsDTO receivedSmsDTO = receivedSmsDtoList.get(0);
			WebClient.create()
				.get()
				.uri(receivedSmsPushUrl, receivedSmsDTO)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToMono(Boolean.class)
				.block();
			receivedSmsDtoList.remove(receivedSmsDTO);
		}
	}
}
