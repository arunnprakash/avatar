/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import static com.kirana.avatar.product.model.MarketPrice_.PRICE;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.ID;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.master.dto.MarketDTO;
import com.kirana.avatar.master.dto.SellerAgentCommissionDTO;
import com.kirana.avatar.master.dto.SellerMerchantCommissionDTO;
import com.kirana.avatar.master.dto.SellerTransportationChargeDTO;
import com.kirana.avatar.master.feign.MarketClient;
import com.kirana.avatar.master.feign.SellerAgentCommissionClient;
import com.kirana.avatar.master.feign.SellerMerchantCommissionClient;
import com.kirana.avatar.master.feign.SellerTransportationChargeClient;
import com.kirana.avatar.product.dto.MarketPriceDTO;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.mapper.MarketPriceMapper;
import com.kirana.avatar.product.model.MarketPrice;
import com.kirana.avatar.product.repositories.MarketPriceRepository;
import com.kirana.avatar.product.repositories.ProductRegionRepository;
import com.kirana.avatar.product.repositories.UserProductRepository;
import com.kirana.avatar.product.service.MarketPriceService;
import com.kirana.avatar.product.service.SellerPriceHistoryService;
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
	private MarketClient marketClient;
	private ObjectMapper objectMapper;
	private SellerPriceHistoryService sellerPriceHistoryService;
	private SellerAgentCommissionClient sellerAgentCommissionClient;
	private SellerMerchantCommissionClient sellerMerchantCommissionClient;
	private SellerTransportationChargeClient sellerTransportationChargeClient;
	public MarketPriceServiceImpl(MarketPriceRepository marketPriceRepository, MarketPriceMapper marketPriceMapper, MarketPriceSpecification marketPriceSpecification,
			UserProductRepository userProductRepository, UserProductSpecification userProductSpecification,
			ProductRegionRepository productRegionRepository, ProductRegionSpecification productRegionSpecification,
			MarketClient marketClient, ObjectMapper objectMapper,
			SellerPriceHistoryService sellerPriceHistoryService,
			SellerAgentCommissionClient sellerAgentCommissionClient,
			SellerMerchantCommissionClient sellerMerchantCommissionClient,
			SellerTransportationChargeClient sellerTransportationChargeClient) {
		super(marketPriceRepository, marketPriceMapper, marketPriceSpecification);
		this.marketPriceRepository = marketPriceRepository;
		this.marketPriceMapper = marketPriceMapper;
		this.marketPriceSpecification = marketPriceSpecification;
		this.userProductRepository = userProductRepository;
		this.userProductSpecification = userProductSpecification;
		this.productRegionRepository = productRegionRepository;
		this.productRegionSpecification = productRegionSpecification;
		this.marketClient = marketClient;
		this.objectMapper = objectMapper;
		this.sellerPriceHistoryService = sellerPriceHistoryService;
		this.sellerAgentCommissionClient = sellerAgentCommissionClient;
		this.sellerTransportationChargeClient = sellerTransportationChargeClient;
		this.sellerMerchantCommissionClient = sellerMerchantCommissionClient;
	}
	@Override
	protected MarketPrice beforeSave(MarketPriceDTO marketPriceDTO, MarketPrice model) {
		model.setMarket((Long)marketPriceDTO.getMarket().get(ID));
		return model;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected MarketPrice afterSave(MarketPriceDTO marketPriceDTO, MarketPrice model) {
		marketPriceDTO = marketPriceDTO.toBuilder().id(model.getId()).build();
		
		SellerAgentCommissionDTO sellerAgentCommission = sellerAgentCommissionClient.getLatestSellerAgentCommission();
		SellerTransportationChargeDTO sellerTransportationCharge = sellerTransportationChargeClient.getLatestSellerTransportationCharge();
		SellerMerchantCommissionDTO sellerMerchantCommission = sellerMerchantCommissionClient.getLatestSellerMerchantCommission();
		
		log.debug("After Save Market Price {}", marketPriceDTO);
		SellerPriceHistoryDTO sellerPriceHistoryDTO = SellerPriceHistoryDTO
				.builder()
				.marketPrice(marketPriceDTO)
				.sellerAgentCommission(objectMapper.convertValue(sellerAgentCommission, Map.class))
				.sellerTransportationCharge(objectMapper.convertValue(sellerTransportationCharge, Map.class))
				.sellerMerchantCommission(objectMapper.convertValue(sellerMerchantCommission, Map.class))
				.price(0.0)
				.product(marketPriceDTO.getProduct())
				.quality(marketPriceDTO.getQuality())
				.build();
		sellerPriceHistoryService.save(sellerPriceHistoryDTO);
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

	@SuppressWarnings("unchecked")
	@Override
	protected MarketPriceDTO afterLoad(MarketPriceDTO resource, MarketPrice model) {
		MarketDTO market = marketClient.get(model.getMarket());
		Map<String, Object> marketMap = objectMapper.convertValue(market, Map.class);
		return resource.toBuilder().market(marketMap).build();
	}

}
