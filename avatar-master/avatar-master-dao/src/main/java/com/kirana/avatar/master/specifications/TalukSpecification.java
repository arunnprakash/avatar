/**
 * 
 */
package com.kirana.avatar.master.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.kirana.avatar.master.model.Taluk;
import com.kirana.avatar.master.model.Taluk_;
import com.kirana.avatar.common.jpa.specification.BaseEntitySpecification;

/**
 * @author __Telmila__
 *
 */

@SuppressWarnings("serial")
@Component
public class TalukSpecification extends BaseEntitySpecification<Taluk>{
	
	public Specification<Taluk> hasTalukCode(final String talukCode) {
		return new Specification<Taluk>() {
			public Predicate toPredicate(Root<Taluk> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get(Taluk_.TALUK_CODE), "%"+talukCode+"%");
			}

		};
	}

}
