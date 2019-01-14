/**
 * 
 */
package com.kirana.avatar.authorization.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.authorization.model.Language;
import com.kirana.avatar.authorization.model.Language_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class LanguageSpecification extends BaseEntitySpecification<Language> {

	public Specification<Language> hasLanguageName(final String languageName) {
		return new Specification<Language>() {
			public Predicate toPredicate(Root<Language> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Language_.LANGUAGE_NAME), "%"+languageName+"%");
			}

		};
	}
	
}
