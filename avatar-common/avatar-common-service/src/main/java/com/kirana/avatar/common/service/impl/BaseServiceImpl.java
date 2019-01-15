/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.service.impl;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.jpa.entity.BaseEntity;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.ID;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.CREATED_BY;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.CREATED_DATE;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.LAST_MODIFIED_BY;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.LAST_MODIFIED_DATE;
import static com.kirana.avatar.common.jpa.entity.BaseEntity_.DELETED;
import static com.kirana.avatar.common.jpa.entity.LocaleEntity_.EN;
import static com.kirana.avatar.common.jpa.entity.LocaleEntity_.TA;
import static com.kirana.avatar.common.jpa.entity.LocaleEntity_.MA;
import static com.kirana.avatar.common.jpa.entity.LocaleEntity_.KA;
import static com.kirana.avatar.common.jpa.entity.LocaleEntity_.TE;
import com.kirana.avatar.common.jpa.repository.BaseRepository;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;
import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.common.service.BaseService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 * @param <M>
 * @param <D>
 *
 */
@Slf4j
@Transactional
@SuppressWarnings("unused")
public abstract class BaseServiceImpl<Model extends BaseEntity<Model>, 
		DTO extends BaseDTO, 
		Mapper extends BaseMapper<DTO, Model>, 
		Repository extends BaseRepository<Model>,
		EntitySpecification extends BaseEntitySpecification<Model>> implements BaseService<DTO> {

	private Repository repository;
	private Mapper mapper;
	private EntitySpecification entitySpecification;
	public BaseServiceImpl(Repository repository, Mapper mapper, EntitySpecification entitySpecification) {
		super();
		this.repository = repository;
		this.mapper = mapper;
		this.entitySpecification = entitySpecification;
		log.debug("Setting Up {} With Mapper {}", this.getClass(), mapper.getClass());
	}

	@Override
	public Boolean delete(List<Long> resourceIds) {
		resourceIds.forEach(resourceId->{
			repository
			.findById(resourceId)
			.<Model>map(Model::markAsDeleted)
			.map(repository::save)
			.orElseThrow(ApiException::resourceNotFound);
		});
		return true;
	}

	@Override
	public Collection<DTO> getAll(boolean includesDeletedResources) {
		if (includesDeletedResources) {
			return repository
					.findAll()
					.stream()
					.map(mapper::toDTO)
					.collect(Collectors.toList());
		} else {
			return repository
					.findAll()
					.stream()
					.map(mapper::toDTO)
					.collect(Collectors.toList());
		}
		
	}

	@Override
	public DTO get(Long roleId) {
		return repository
				.findById(roleId)
				.map(mapper::toDTO)
				.orElseThrow(ApiException::resourceNotFound);
	}

	@Override
	public DTO save(DTO resource) {
		if(null != resource.getId() && repository.existsById(resource.getId())) {
			throw ApiException.resourceAlreadyExist();
		} else {
			Model model = mapper.toModel(resource);
			model = repository.save(model);
			return mapper.toDTO(model);
		}
	}

	@Override
	public DTO update(DTO resource) {
		return repository
				.findById(resource.getId())
				.map(model -> {
					return mapper.updateModel(resource, model);
				})
				.map(repository::save)
				.map(mapper::toDTO)
				.orElseThrow(ApiException::resourceNotFound);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagingAndFilterResponse<DTO> getResourceByFilterAndPaging(
			PagingAndFilterRequest pagingAndFilterRequest, boolean includesDeletedResources) {
		String sortBy = (null != pagingAndFilterRequest.getSortBy())? pagingAndFilterRequest.getSortBy() : "id";
		Sort.Direction sortDirection = (null != pagingAndFilterRequest.getSortingOrder() && pagingAndFilterRequest.getSortingOrder().equalsIgnoreCase("ASC"))? Sort.Direction.ASC : Sort.Direction.DESC  ;
		Pageable pageRequest = PageRequest.of(pagingAndFilterRequest.getPageNumber(), 
				pagingAndFilterRequest.getPageSize(), 
				sortDirection, 
				sortBy);
		log.debug("getResourceByFilterAndPaging paging Parameter {} includes deleted resource {}", pageRequest, includesDeletedResources);
		Long totalRecords = null;
		List<BaseDTO> results = null;
		if (null == pagingAndFilterRequest.getFilters() 
				|| pagingAndFilterRequest.getFilters().isEmpty()) {
			if (includesDeletedResources) {
				Specification<Model> specification = Specification.where(entitySpecification.hasDeletedNotNull());
				totalRecords = repository.count(specification);
				results = repository
						.findAll(specification, pageRequest)
						.stream()
						.map(mapper::toDTO)
						.collect(Collectors.toList());
			} else {
				totalRecords = repository.countByDeleted(includesDeletedResources);
				results = repository
						.findByDeleted(includesDeletedResources, pageRequest)
						.stream()
						.map(mapper::toDTO)
						.collect(Collectors.toList());
			}
		} else {
			Specification<Model> specification = null;
			if (includesDeletedResources) {
				specification = Specification.where(entitySpecification.hasDeletedNotNull());
			} else {
				specification = Specification.where(entitySpecification.hasDeleted(includesDeletedResources));
			}
			for (FilterCriteria filter : pagingAndFilterRequest.getFilters()) {
				String itemName = filter.getFilterByItem();
				if (itemName.equalsIgnoreCase(ID)) {
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						Long id = Long.parseLong(itemValue);
						spec = (spec == null) ? entitySpecification.hasId(id) : spec.or(entitySpecification.hasId(id));
					}
					log.debug("Adding specification {} {}", ID, spec);
					specification = specification.and(spec);
				} else if (itemName.equalsIgnoreCase(CREATED_BY)) {
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasCreatedBy(itemValue) : spec.or(entitySpecification.hasCreatedBy(itemValue));
					}
					log.debug("Adding specification {} {}", CREATED_BY, specification);
					specification = specification.and(spec);
				} else if (itemName.equalsIgnoreCase(LAST_MODIFIED_BY)) {
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasModifiedBy(itemValue) : spec.or(entitySpecification.hasModifiedBy(itemValue));
					}
					log.debug("Adding specification {} {}", LAST_MODIFIED_BY, spec);
					specification = specification.and(spec);
				} else if (itemName.equalsIgnoreCase(CREATED_DATE)) {
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						ZonedDateTime date = ZonedDateTime.parse(itemValue);
						spec = (spec == null) ? entitySpecification.hasCreatedDate(date) : spec.or(entitySpecification.hasCreatedDate(date));
					}
					log.debug("Adding specification {} {}", CREATED_DATE, spec);
					specification = specification.and(spec);
				} else if (itemName.equalsIgnoreCase(LAST_MODIFIED_DATE)) {
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						ZonedDateTime date = ZonedDateTime.parse(itemValue);
						spec = (spec == null) ? entitySpecification.hasModifiedDate(date) : spec.or(entitySpecification.hasModifiedDate(date));
					}
					log.debug("Adding specification {} {}", LAST_MODIFIED_DATE, spec);
					specification = specification.and(spec);
				}  else if (itemName.equalsIgnoreCase(DELETED)) {
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						Boolean deletedStatus = itemValue.equals("1");
						spec = (spec == null) ? entitySpecification.hasDeleted(deletedStatus) : spec.or(entitySpecification.hasDeleted(deletedStatus));
					}
					log.debug("Adding specification {} {}", DELETED, spec);
					specification = specification.and(spec);
				} else if(itemName.equalsIgnoreCase(EN)){
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasEnglish(itemValue) : spec.or(entitySpecification.hasEnglish(itemValue));
					}
					log.debug("Adding specification {} {}", EN, spec);
					specification = specification.and(spec);
				} else if(itemName.equalsIgnoreCase(TA)){
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasTamil(itemValue) : spec.or(entitySpecification.hasTamil(itemValue));
					}
					log.debug("Adding specification {} {}", TA, spec);
					specification = specification.and(spec);
				} else if(itemName.equalsIgnoreCase(MA)){
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasMalayalam(itemValue) : spec.or(entitySpecification.hasMalayalam(itemValue));
					}
					log.debug("Adding specification {} {}", MA, spec);
					specification = specification.and(spec);
				} else if(itemName.equalsIgnoreCase(KA)){
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasKanadam(itemValue) : spec.or(entitySpecification.hasKanadam(itemValue));
					}
					log.debug("Adding specification {} {}", KA, spec);
					specification = specification.and(spec);
				} else if(itemName.equalsIgnoreCase(TE)){
					Specification<Model> spec = null;
					for (String itemValue : filter.getFilterByItemValues()) {
						spec = (spec == null) ? entitySpecification.hasTelugu(itemValue) : spec.or(entitySpecification.hasTelugu(itemValue));
					}
					log.debug("Adding specification {} {}", TE, spec);
					specification = specification.and(spec);
				} else {
					specification = getSpecification(filter, specification);
				}
			}
			log.debug("specification paging Parameter {}", specification);
			totalRecords = repository.count(specification);
			results = repository
					.findAll(specification, pageRequest)
					.stream()
					.map(mapper::toDTO)
					.collect(Collectors.toList());
		}
		PagingAndFilterResponse<DTO> response = (PagingAndFilterResponse<DTO>) PagingAndFilterResponse
				.builder()
				.totalRecords(totalRecords)
				.results(results)
				.build();
		log.debug("getResourceByFilterAndPaging paging Parameter {}  includes deleted resource {} results {}", pageRequest, includesDeletedResources, response);
		return response;
	}

	protected abstract Specification<Model> getSpecification(FilterCriteria filter, Specification<Model> specification);
}
