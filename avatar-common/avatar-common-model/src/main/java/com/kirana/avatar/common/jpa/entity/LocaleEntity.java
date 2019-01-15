/**
 * 
 */
package com.kirana.avatar.common.jpa.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author __Telmila__
 *
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class LocaleEntity<Model extends BaseEntity<Model>> extends BaseEntity<Model> {

	@Column(nullable = true)
	protected String en;
	@Column(nullable = true)
	protected String ta;
	@Column(nullable = true)
	protected String ma;
	@Column(nullable = true)
	protected String ka;
	@Column(nullable = true)
	protected String te;
}
