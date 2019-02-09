/**
 * 
 */
package com.kirana.avatar.product.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kirana.avatar.common.jpa.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author __Telmila__
 *
 */

@Entity
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Holiday extends BaseEntity<Holiday>{

	@Column(nullable = false)
	protected ZonedDateTime startDate;
	@Column(nullable = false)
	protected ZonedDateTime endDate;
	@Column(nullable = false)
	protected String description;
	@Column(name="state_id" ,nullable = false)
	protected Long state;
	@Column(name="district_id",nullable = false)
	protected Long district;
	@Column(name="taluk_id", nullable = false)
	protected Long taluk;
		
}
