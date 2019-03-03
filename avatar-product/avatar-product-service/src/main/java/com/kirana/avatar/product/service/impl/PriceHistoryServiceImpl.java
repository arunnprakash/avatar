/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.kirana.avatar.product.model.PriceHistory_.PRICE;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.PriceHistoryDTO;
import com.kirana.avatar.product.mapper.PriceHistoryMapper;
import com.kirana.avatar.product.model.PriceHistory;
import com.kirana.avatar.product.model.Product;
import com.kirana.avatar.product.model.ProductRegion;
import com.kirana.avatar.product.model.UserProduct;
import com.kirana.avatar.product.repositories.PriceHistoryRepository;
import com.kirana.avatar.product.repositories.ProductRegionRepository;
import com.kirana.avatar.product.repositories.UserProductRepository;
import com.kirana.avatar.product.service.PriceHistoryService;
import com.kirana.avatar.product.specifications.PriceHistorySpecification;
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
public class PriceHistoryServiceImpl extends BaseServiceImpl<PriceHistory, PriceHistoryDTO, PriceHistoryMapper, PriceHistoryRepository, PriceHistorySpecification> implements PriceHistoryService{
	
	private PriceHistoryRepository priceHistoryRepository;
	private PriceHistoryMapper priceHistoryMapper;
	private PriceHistorySpecification priceHistorySpecification;
	private UserProductRepository userProductRepository;
	private UserProductSpecification userProductSpecification;
	private ProductRegionRepository productRegionRepository;
	private ProductRegionSpecification productRegionSpecification;
	public PriceHistoryServiceImpl(PriceHistoryRepository priceHistoryRepository, PriceHistoryMapper priceHistoryMapper, PriceHistorySpecification priceHistorySpecification,
			UserProductRepository userProductRepository, UserProductSpecification userProductSpecification,
			ProductRegionRepository productRegionRepository, ProductRegionSpecification productRegionSpecification) {
		super(priceHistoryRepository, priceHistoryMapper, priceHistorySpecification);
		this.priceHistoryRepository = priceHistoryRepository;
		this.priceHistoryMapper = priceHistoryMapper;
		this.priceHistorySpecification = priceHistorySpecification;
		this.userProductRepository = userProductRepository;
		this.userProductSpecification = userProductSpecification;
		this.productRegionRepository = productRegionRepository;
		this.productRegionSpecification = productRegionSpecification;
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
	@Override
	public PagingAndFilterResponse<BaseDTO> getProductsForUser(PagingAndFilterRequest pagingAndFilterRequest, Long userId, Long talukId, Long districtId, Long stateId) {
		Set<Product> products = new LinkedHashSet<>();
		products.addAll(getProductsMappedToUser(userId));
		products.addAll(getProductsMappedToTaluk(talukId));
		products.addAll(getProductsMappedToDistrict(districtId));
		products.addAll(getProductsMappedToState(stateId));
		Long totalRecords = Long.valueOf(products.size());
		Collection<BaseDTO> results = products.stream()
				.map(this::getLatestPrice)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.map(priceHistoryMapper::toDTO)
				.collect(Collectors.toList());
		PagingAndFilterResponse<BaseDTO> response = PagingAndFilterResponse
				.builder()
				.totalRecords(totalRecords)
				.results(results)
				.build();
		return response;
	}
	private Optional<PriceHistory> getLatestPrice(Product product) {
		List<PriceHistory> priceHistories = priceHistoryRepository.getLatestPrice(product.getId());
		if (null != priceHistories && !priceHistories.isEmpty()) {
			return priceHistories.stream().findFirst();
		} else {
			return Optional.ofNullable(null);
		}
	}
	private List<Product> getProductsMappedToState(Long stateId) {
		Specification<ProductRegion> specification = Specification.where(productRegionSpecification.hasDeleted(false));
		specification = specification.and(productRegionSpecification.hasStateId(stateId));
		List<Product> results = productRegionRepository
				.findAll(specification)
				.stream()
				.map(ProductRegion::getProduct)
				.collect(Collectors.toList());
		return results;
	}
	private List<Product> getProductsMappedToDistrict(Long districtId) {
		Specification<ProductRegion> specification = Specification.where(productRegionSpecification.hasDeleted(false));
		specification = specification.and(productRegionSpecification.hasDistrictId(districtId));
		List<Product> results = productRegionRepository
				.findAll(specification)
				.stream()
				.map(ProductRegion::getProduct)
				.collect(Collectors.toList());
		return results;
	}
	private List<Product> getProductsMappedToTaluk(Long talukId) {
		Specification<ProductRegion> specification = Specification.where(productRegionSpecification.hasDeleted(false));
		specification = specification.and(productRegionSpecification.hasTalukId(talukId));
		List<Product> results = productRegionRepository
				.findAll(specification)
				.stream()
				.map(ProductRegion::getProduct)
				.collect(Collectors.toList());
		return results;
	}
	private List<Product> getProductsMappedToUser(Long userId) {
		Specification<UserProduct> specification = Specification.where(userProductSpecification.hasDeleted(false));
		specification = specification.and(userProductSpecification.hasUserId(userId));
		List<Product> results = userProductRepository
				.findAll(specification)
				.stream()
				.map(UserProduct::getProduct)
				.collect(Collectors.toList());
		return results;
	}
	@Override
	public PriceHistoryDTO getPriceForProduct(Long productId, Long qualityId, String pricePublishedDate) {
		//ZonedDateTime createdDate = ZonedDateTime.parse(pricePublishedDate, DateTimeFormatter.ISO_DATE.withZone(ZoneId.systemDefault()));
		Instant instant = Instant.parse(pricePublishedDate);
		ZonedDateTime createdDate = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
		return getLatestPrice(productId, qualityId, createdDate)
				.map(priceHistoryMapper::toDTO)
				.orElseThrow(ApiException::resourceNotFound);
	}
	private Optional<PriceHistory> getLatestPrice(Long productId, Long qualityId, ZonedDateTime createdDate) {
		List<PriceHistory> priceHistories = priceHistoryRepository.getLatestPrice(productId, qualityId, createdDate);
		if (null != priceHistories && !priceHistories.isEmpty()) {
			return priceHistories.stream().findFirst();
		} else {
			return Optional.ofNullable(null);
		}
	}
}
