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
import com.kirana.avatar.offline.dto.OutgoingSmsDTO;
import com.kirana.avatar.offline.mapper.OutgoingSmsMapper;
import com.kirana.avatar.offline.model.OutgoingSms;
import com.kirana.avatar.offline.repositories.OutgoingSmsRepository;
import com.kirana.avatar.offline.repositories.SmsProcessedStatusRepository;
import com.kirana.avatar.offline.service.OutgoingSmsService;
import com.kirana.avatar.offline.specifications.OutgoingSmsSpecification;
import com.kirana.avatar.offline.specifications.SmsProcessedStatusSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
public class OutgoingSmsServiceImpl extends BaseServiceImpl<OutgoingSms, OutgoingSmsDTO, OutgoingSmsMapper, OutgoingSmsRepository, OutgoingSmsSpecification> implements OutgoingSmsService {

	private OutgoingSmsRepository outgoingSmsRepository;
	private OutgoingSmsMapper outgoingSmsMapper;
	private OutgoingSmsSpecification outgoingSmsSpecification;

	private SmsProcessedStatusRepository smsProcessedStatusRepository;
	private SmsProcessedStatusSpecification smsProcessedStatusSpecification;
	
	private UserClient userClient;
	private ObjectMapper objectMapper;
	public OutgoingSmsServiceImpl(OutgoingSmsRepository outgoingSmsRepository, OutgoingSmsMapper outgoingSmsMapper, 
			OutgoingSmsSpecification outgoingSmsSpecification, 
			SmsProcessedStatusRepository smsProcessedStatusRepository, 
			SmsProcessedStatusSpecification smsProcessedStatusSpecification,
			UserClient userClient,
			ObjectMapper objectMapper) {
		super(outgoingSmsRepository, outgoingSmsMapper, outgoingSmsSpecification);
		this.outgoingSmsRepository = outgoingSmsRepository;
		this.smsProcessedStatusRepository = smsProcessedStatusRepository;
		this.smsProcessedStatusSpecification = smsProcessedStatusSpecification;
		this.outgoingSmsMapper = outgoingSmsMapper;
		this.outgoingSmsSpecification = outgoingSmsSpecification;
		this.userClient = userClient;
		this.objectMapper = objectMapper;
	}

	@Override
	protected OutgoingSms beforeUpdate(OutgoingSmsDTO outgoingSmsDTO, OutgoingSms model) {
		return model;
	}

	@Override
	protected OutgoingSms afterUpdate(OutgoingSmsDTO outgoingSmsDTO, OutgoingSms model) {
		return model;
	}

	@Override
	protected Specification<OutgoingSms> getSpecification(FilterCriteria filter, Specification<OutgoingSms> specification) {
		return specification;
	}

	@Override
	protected OutgoingSmsDTO afterLoad(OutgoingSmsDTO resource, OutgoingSms model) {
		return resource;
	}

	@Override
	protected OutgoingSms beforeSave(OutgoingSmsDTO resource, OutgoingSms model) {
		return model;
	}

	@Override
	protected OutgoingSms afterSave(OutgoingSmsDTO outgoingSmsDTO, OutgoingSms model) {
		return model;
	}
}
