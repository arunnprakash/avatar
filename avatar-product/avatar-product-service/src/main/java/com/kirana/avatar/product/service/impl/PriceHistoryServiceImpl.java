/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.kirana.avatar.product.model.PriceHistory_.PRICE;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.PriceHistoryDTO;
import com.kirana.avatar.product.mapper.PriceHistoryMapper;
import com.kirana.avatar.product.model.PriceHistory;
import com.kirana.avatar.product.repositories.PriceHistoryRepository;
import com.kirana.avatar.product.service.PriceHistoryService;
import com.kirana.avatar.product.specifications.PriceHistorySpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class PriceHistoryServiceImpl extends BaseServiceImpl<PriceHistory, PriceHistoryDTO, PriceHistoryMapper, PriceHistoryRepository, PriceHistorySpecification> implements PriceHistoryService{
	
	private PriceHistoryRepository priceHistoryRepository;
	private PriceHistoryMapper priceHistoryMapper;
	private PriceHistorySpecification priceHistorySpecification;
	public PriceHistoryServiceImpl(PriceHistoryRepository priceHistoryRepository, PriceHistoryMapper priceHistoryMapper,PriceHistorySpecification priceHistorySpecification) {
		super(priceHistoryRepository, priceHistoryMapper, priceHistorySpecification);
		this.priceHistoryRepository = priceHistoryRepository;
		this.priceHistoryMapper = priceHistoryMapper;
		this.priceHistorySpecification = priceHistorySpecification;
	}
	@Override
	protected PriceHistory beforeSave(PriceHistory model) {
		return model;
	}
	@Override
	protected PriceHistory afterSave(PriceHistoryDTO priceHistoryDTO, PriceHistory model) {
		return model;
	}
	@Override
	protected PriceHistory beforeUpdate(PriceHistoryDTO resource, PriceHistory model) {
		return model;
	}
	@Override
	protected PriceHistory afterUpdate(PriceHistoryDTO resource, PriceHistory model) {
		return model;
	}
	@Override
	protected Specification<PriceHistory> getSpecification(FilterCriteria filter, Specification<PriceHistory> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(PRICE)) {
			Specification<PriceHistory> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? priceHistorySpecification.hasPrice(itemValue) : spec.or(priceHistorySpecification.hasPrice(itemValue));
			}
			log.debug("Adding specification {} {}", PRICE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
