/**
 * 
 */
package com.kirana.avatar.master.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.master.dto.MarketDTO;
import com.kirana.avatar.master.mapper.MarketMapper;
import com.kirana.avatar.master.model.Market;
import com.kirana.avatar.master.repositories.TalukRepository;
import com.kirana.avatar.master.repositories.MarketRepository;
import com.kirana.avatar.master.service.MarketService;
import com.kirana.avatar.master.specifications.MarketSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */

@Slf4j
@Service
@SuppressWarnings("unused")
public class MarketServiceImpl extends BaseServiceImpl<Market, MarketDTO, MarketMapper, MarketRepository, MarketSpecification> implements MarketService{
	
	private MarketRepository marketRepository;
	private MarketMapper marketMapper;
	private MarketSpecification marketSpecification;
	private TalukRepository talukRepository;
	
	public MarketServiceImpl(MarketRepository marketRepository, MarketMapper marketMapper, MarketSpecification marketSpecification,
			TalukRepository talukRepository) {
		super(marketRepository, marketMapper, marketSpecification);
		this.marketRepository = marketRepository;
		this.marketMapper = marketMapper;
		this.marketSpecification = marketSpecification;
		this.talukRepository = talukRepository;
	}

	@Override
	protected Market beforeSave(Market model) {
		return model;
	}

	@Override
	protected Market beforeUpdate(MarketDTO marketDTO, Market model) {
		return talukRepository
				.findById(marketDTO.getTaluk().getId())
				.map(taluk -> {
					model.setTaluk(taluk);
					return model;
				})
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	protected Market afterSave(MarketDTO marketDTO, Market model) {
		return model;
	}

	@Override
	protected Market afterUpdate(MarketDTO marketDTO, Market model) {
		return model;
	}

	@Override
	protected Specification<Market> getSpecification(FilterCriteria filter, Specification<Market> specification) {
		return specification;
	}

}
