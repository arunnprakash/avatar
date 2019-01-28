/**
 * 
 */
package com.kirana.avatar.product.service.impl;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.kirana.avatar.product.model.Quality_.QUALITY_TYPE;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;
import com.kirana.avatar.product.dto.QualityDTO;
import com.kirana.avatar.product.mapper.QualityMapper;
import com.kirana.avatar.product.model.Quality;
import com.kirana.avatar.product.repositories.QualityRepository;
import com.kirana.avatar.product.service.QualityService;
import com.kirana.avatar.product.specifications.QualitySpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */
@Slf4j
@Service
@SuppressWarnings("unused")
@Transactional
public class QualityServiceImpl extends BaseServiceImpl<Quality, QualityDTO, QualityMapper, QualityRepository, QualitySpecification> implements QualityService{
	
	private QualityRepository qualityRepository;
	private QualityMapper qualityMapper;
	private QualitySpecification qualitySpecification;
	public QualityServiceImpl(QualityRepository qualityRepository, QualityMapper qualityMapper,QualitySpecification qualitySpecification) {
		super(qualityRepository, qualityMapper, qualitySpecification);
		this.qualityRepository = qualityRepository;
		this.qualityMapper = qualityMapper;
		this.qualitySpecification = qualitySpecification;
	}
	@Override
	protected Quality beforeSave(Quality model) {
		return model;
	}
	@Override
	protected Quality afterSave(Quality model) {
		return model;
	}
	@Override
	protected Quality beforeUpdate(QualityDTO resource, Quality model) {
		return model;
	}
	@Override
	protected Quality afterUpdate(QualityDTO resource, Quality model) {
		return model;
	}
	@Override
	protected Specification<Quality> getSpecification(FilterCriteria filter, Specification<Quality> specification) {
		String itemName = filter.getFilterByItem();
		if (itemName.equalsIgnoreCase(QUALITY_TYPE)) {
			Specification<Quality> spec = null;
			for (String itemValue : filter.getFilterByItemValues()) {
				spec = (spec == null) ? qualitySpecification.hasQualityType(itemValue) : spec.or(qualitySpecification.hasQualityType(itemValue));
			}
			log.debug("Adding specification {} {}", QUALITY_TYPE, spec);
			specification = specification.and(spec);
		} else {
			throw ApiException.implementationNotFound();
		}
		return specification;
	}

}
