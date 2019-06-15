package com.kirana.avatar.offline.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.offline.dto.IncomingSmsDTO;
import com.kirana.avatar.offline.mapper.IncomingSmsMapper;
import com.kirana.avatar.offline.model.IncomingSms;
import com.kirana.avatar.offline.repositories.IncomingSmsRepository;
import com.kirana.avatar.offline.repositories.SmsProcessedStatusRepository;
import com.kirana.avatar.offline.service.IncomingSmsService;
import com.kirana.avatar.offline.specifications.IncomingSmsSpecification;
import com.kirana.avatar.offline.specifications.SmsProcessedStatusSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class IncomingSmsServiceImpl extends BaseServiceImpl<IncomingSms, IncomingSmsDTO, IncomingSmsMapper, IncomingSmsRepository, IncomingSmsSpecification> implements IncomingSmsService {

	private IncomingSmsRepository incomingSmsRepository;
	private IncomingSmsMapper incomingSmsMapper;
	private IncomingSmsSpecification incomingSmsSpecification;

	private SmsProcessedStatusRepository smsProcessedStatusRepository;
	private SmsProcessedStatusSpecification smsProcessedStatusSpecification;
	
	private UserClient userClient;
	private ObjectMapper objectMapper;
	public IncomingSmsServiceImpl(IncomingSmsRepository incomingSmsRepository, IncomingSmsMapper incomingSmsMapper, 
			IncomingSmsSpecification incomingSmsSpecification, 
			SmsProcessedStatusRepository smsProcessedStatusRepository, 
			SmsProcessedStatusSpecification smsProcessedStatusSpecification,
			UserClient userClient,
			ObjectMapper objectMapper) {
		super(incomingSmsRepository, incomingSmsMapper, incomingSmsSpecification);
		this.incomingSmsRepository = incomingSmsRepository;
		this.smsProcessedStatusRepository = smsProcessedStatusRepository;
		this.smsProcessedStatusSpecification = smsProcessedStatusSpecification;
		this.incomingSmsMapper = incomingSmsMapper;
		this.incomingSmsSpecification = incomingSmsSpecification;
		this.userClient = userClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected IncomingSms beforeUpdate(IncomingSmsDTO incomingSmsDTO, IncomingSms model) {
		return model;
	}

	@Override
	protected IncomingSms afterUpdate(IncomingSmsDTO incomingSmsDTO, IncomingSms model) {
		return model;
	}

	@Override
	protected Specification<IncomingSms> getSpecification(FilterCriteria filter, Specification<IncomingSms> specification) {
		return specification;
	}

	@Override
	protected IncomingSmsDTO afterLoad(IncomingSmsDTO resource, IncomingSms model) {
		return resource;
	}

	@Override
	protected IncomingSms beforeSave(IncomingSmsDTO resource, IncomingSms model) {
		return model;
	}

	@Override
	protected IncomingSms afterSave(IncomingSmsDTO incomingSmsDTO, IncomingSms model) {
		return model;
	}
}
