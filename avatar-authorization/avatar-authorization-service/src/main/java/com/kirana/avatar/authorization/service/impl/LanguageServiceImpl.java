/**
 * 
 */
package com.kirana.avatar.authorization.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kirana.avatar.authorization.dto.LanguageDTO;
import com.kirana.avatar.authorization.mapper.LanguageMapper;
import com.kirana.avatar.authorization.model.Language;
import com.kirana.avatar.authorization.repositories.LanguageRepository;
import com.kirana.avatar.authorization.service.LanguageService;
import com.kirana.avatar.authorization.specifications.LanguageSpecification;
import com.kirana.avatar.common.dto.FilterCriteria;
import com.kirana.avatar.common.service.impl.BaseServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __Telmila__
 *
 */

@Slf4j
@Service
@SuppressWarnings("unused")
public class LanguageServiceImpl extends BaseServiceImpl<Language, LanguageDTO, LanguageMapper, LanguageRepository, LanguageSpecification> implements LanguageService{
	
	private LanguageRepository languageRepository;
	private LanguageMapper languageMapper;
	private LanguageSpecification languageSpecification;
	
	public LanguageServiceImpl(LanguageRepository languageRepository, LanguageMapper languageMapper,
			LanguageSpecification languageSpecification) {
		super(languageRepository, languageMapper, languageSpecification);
		this.languageRepository = languageRepository;
		this.languageMapper = languageMapper;
		this.languageSpecification = languageSpecification;
	}

	@Override
	protected Language beforeSave(Language model) {
		return model;
	}

	@Override
	protected Language beforeUpdate(LanguageDTO languageDTO, Language model) {
		return model;
	}

	@Override
	protected Language afterSave(LanguageDTO languageDTO, Language model) {
		return model;
	}

	@Override
	protected Language afterUpdate(LanguageDTO languageDTO, Language model) {
		return model;
	}

	@Override
	protected Specification<Language> getSpecification(FilterCriteria filter, Specification<Language> specification) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
