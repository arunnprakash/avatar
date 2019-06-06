/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import static com.kirana.avatar.product.model.MarketPrice_.PRICE;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.MarketPriceDTO;
import com.kirana.avatar.product.mapper.MarketPriceMapper;
import com.kirana.avatar.product.model.MarketPrice;
import com.kirana.avatar.product.repositories.MarketPriceRepository;
import com.kirana.avatar.product.repositories.ProductRegionRepository;
import com.kirana.avatar.product.repositories.UserProductRepository;
import com.kirana.avatar.product.service.MarketPriceService;
import com.kirana.avatar.product.specifications.MarketPriceSpecification;
import com.kirana.avatar.product.specifications.ProductRegionSpecification;
import com.kirana.avatar.product.specifications.UserProductSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class MarketPriceServiceImpl extends BaseServiceImpl<MarketPrice, MarketPriceDTO, MarketPriceMapper, MarketPriceRepository, MarketPriceSpecification> implements MarketPriceService{
	
	private MarketPriceRepository marketPriceRepository;
	private MarketPriceMapper marketPriceMapper;
	private MarketPriceSpecification marketPriceSpecification;
	private UserProductRepository userProductRepository;
	private UserProductSpecification userProductSpecification;
	private ProductRegionRepository productRegionRepository;
	private ProductRegionSpecification productRegionSpecification;
	public MarketPriceServiceImpl(MarketPriceRepository marketPriceRepository, MarketPriceMapper marketPriceMapper, MarketPriceSpecification marketPriceSpecification,
			UserProductRepository userProductRepository, UserProductSpecification userProductSpecification,
			ProductRegionRepository productRegionRepository, ProductRegionSpecification productRegionSpecification) {
		super(marketPriceRepository, marketPriceMapper, marketPriceSpecification);
		this.marketPriceRepository = marketPriceRepository;
		this.marketPriceMapper = marketPriceMapper;
		this.marketPriceSpecification = marketPriceSpecification;
		this.userProductRepository = userProductRepository;
		this.userProductSpecification = userProductSpecification;
		this.productRegionRepository = productRegionRepository;
		this.productRegionSpecification = productRegionSpecification;
	}
	@Override
	protected MarketPrice beforeSave(MarketPrice model) {
		return model;
	}
	@Override
	protected MarketPrice afterSave(MarketPriceDTO marketPriceDTO, MarketPrice model) {
		return model;
	}
	@Override
	protected MarketPrice beforeUpdate(MarketPriceDTO resource, MarketPrice model) {
		return model;
	}
	@Override
	protected MarketPrice afterUpdate(MarketPriceDTO resource, MarketPrice model) {
		return model;
	}
	@Override
	protected Specification<MarketPrice> getSpecification(FilterCriteria filter, Specification<MarketPrice> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(PRICE)) {
			Specification<MarketPrice> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? marketPriceSpecification.hasPrice(itemValue) : spec.or(marketPriceSpecification.hasPrice(itemValue));
			}
			log.debug("Adding specification {} {}", PRICE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
