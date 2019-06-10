/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.kirana.avatar.product.model.SellerPriceHistory_.PRICE;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.SellerPriceHistoryDTO;
import com.kirana.avatar.product.mapper.SellerPriceHistoryMapper;
import com.kirana.avatar.product.model.SellerPriceHistory;
import com.kirana.avatar.product.model.Product;
import com.kirana.avatar.product.model.ProductRegion;
import com.kirana.avatar.product.model.UserProduct;
import com.kirana.avatar.product.repositories.SellerPriceHistoryRepository;
import com.kirana.avatar.product.repositories.ProductRegionRepository;
import com.kirana.avatar.product.repositories.UserProductRepository;
import com.kirana.avatar.product.service.SellerPriceHistoryService;
import com.kirana.avatar.product.specifications.SellerPriceHistorySpecification;
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
public class SellerPriceHistoryServiceImpl extends BaseServiceImpl<SellerPriceHistory, SellerPriceHistoryDTO, SellerPriceHistoryMapper, SellerPriceHistoryRepository, SellerPriceHistorySpecification> implements SellerPriceHistoryService{
	
	private SellerPriceHistoryRepository priceHistoryRepository;
	private SellerPriceHistoryMapper priceHistoryMapper;
	private SellerPriceHistorySpecification priceHistorySpecification;
	private UserProductRepository userProductRepository;
	private UserProductSpecification userProductSpecification;
	private ProductRegionRepository productRegionRepository;
	private ProductRegionSpecification productRegionSpecification;
	public SellerPriceHistoryServiceImpl(SellerPriceHistoryRepository priceHistoryRepository, SellerPriceHistoryMapper priceHistoryMapper, SellerPriceHistorySpecification priceHistorySpecification,
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
	protected SellerPriceHistory beforeSave(SellerPriceHistoryDTO priceHistoryDTO, SellerPriceHistory model) {
		return model;
	}
	@Override
	protected SellerPriceHistory afterSave(SellerPriceHistoryDTO priceHistoryDTO, SellerPriceHistory model) {
		return model;
	}
	@Override
	protected SellerPriceHistory beforeUpdate(SellerPriceHistoryDTO resource, SellerPriceHistory model) {
		return model;
	}
	@Override
	protected SellerPriceHistory afterUpdate(SellerPriceHistoryDTO resource, SellerPriceHistory model) {
		return model;
	}
	@Override
	protected Specification<SellerPriceHistory> getSpecification(FilterCriteria filter, Specification<SellerPriceHistory> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(PRICE)) {
			Specification<SellerPriceHistory> spec = null;
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

		return PagingAndFilterResponse
				.builder()
				.totalRecords(totalRecords)
				.results(results)
				.build();
	}
	private Optional<SellerPriceHistory> getLatestPrice(Product product) {
		List<SellerPriceHistory> priceHistories = priceHistoryRepository.getLatestPrice(product.getId());
		if (null != priceHistories && !priceHistories.isEmpty()) {
			return priceHistories.stream().findFirst();
		} else {
			return Optional.ofNullable(null);
		}
	}
	private List<Product> getProductsMappedToState(Long stateId) {
		Specification<ProductRegion> specification = Specification.where(productRegionSpecification.hasDeleted(false));
		specification = specification.and(productRegionSpecification.hasStateId(stateId));
		return productRegionRepository
				.findAll(specification)
				.stream()
				.map(ProductRegion::getProduct)
				.collect(Collectors.toList());
	}
	private List<Product> getProductsMappedToDistrict(Long districtId) {
		Specification<ProductRegion> specification = Specification.where(productRegionSpecification.hasDeleted(false));
		specification = specification.and(productRegionSpecification.hasDistrictId(districtId));
		return productRegionRepository
				.findAll(specification)
				.stream()
				.map(ProductRegion::getProduct)
				.collect(Collectors.toList());
	}
	private List<Product> getProductsMappedToTaluk(Long talukId) {
		Specification<ProductRegion> specification = Specification.where(productRegionSpecification.hasDeleted(false));
		specification = specification.and(productRegionSpecification.hasTalukId(talukId));
		return productRegionRepository
				.findAll(specification)
				.stream()
				.map(ProductRegion::getProduct)
				.collect(Collectors.toList());
	}
	private List<Product> getProductsMappedToUser(Long userId) {
		Specification<UserProduct> specification = Specification.where(userProductSpecification.hasDeleted(false));
		specification = specification.and(userProductSpecification.hasUserId(userId));
		return userProductRepository
				.findAll(specification)
				.stream()
				.map(UserProduct::getProduct)
				.collect(Collectors.toList());
	}
	@Override
	public SellerPriceHistoryDTO getPriceForProduct(Long productId, Long qualityId, String pricePublishedDate) {
		//ZonedDateTime createdDate = ZonedDateTime.parse(pricePublishedDate, DateTimeFormatter.ISO_DATE.withZone(ZoneId.systemDefault()));
		Instant instant = Instant.parse(pricePublishedDate);
		ZonedDateTime createdDate = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
		return getLatestPrice(productId, qualityId, createdDate)
				.map(priceHistoryMapper::toDTO)
				.orElseThrow(ApiException::resourceNotFound);
	}
	private Optional<SellerPriceHistory> getLatestPrice(Long productId, Long qualityId, ZonedDateTime createdDate) {
		List<SellerPriceHistory> priceHistories = priceHistoryRepository.getLatestPrice(productId, qualityId, createdDate);
		if (null != priceHistories && !priceHistories.isEmpty()) {
			return priceHistories.stream().findFirst();
		} else {
			return Optional.ofNullable(null);
		}
	}
	@Override
	protected SellerPriceHistoryDTO afterLoad(SellerPriceHistoryDTO resource, SellerPriceHistory model) {
		return resource;
	}
}
