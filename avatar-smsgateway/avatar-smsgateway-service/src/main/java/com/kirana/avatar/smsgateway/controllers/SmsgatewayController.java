package com.kirana.avatar.smsgateway.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kirana.avatar.smsgateway.dto.ReceivedSmsDTO;
import com.kirana.avatar.smsgateway.dto.SendSmsDTO;
import com.kirana.avatar.smsgateway.service.SmsService;

/**
 * @author __Telmila__
 *
 */

@RestController
public class SmsgatewayController {

	@Autowired
	private SmsService smsService; 

	@PostMapping("send-sms")
	public Boolean sendSms(@RequestBody SendSmsDTO sendSmsDTO) {
		return smsService.send(sendSmsDTO);
	}

	@GetMapping("get-send-sms-list")
	public List<SendSmsDTO> getSendSmsList() {
		return smsService.getSendSmsList();
	}

	@PostMapping("send-sms-completed")
	public Boolean sendSmsCompleted(@RequestBody List<SendSmsDTO> sendSmsDtoList) {
		return smsService.sendSmsCompleted(sendSmsDtoList);
	}

	@PostMapping("receive-sms")
	public Boolean receiveSms(@RequestBody ReceivedSmsDTO receivedSmsDTO) {
		return smsService.receiveSms(receivedSmsDTO);
	}
}
