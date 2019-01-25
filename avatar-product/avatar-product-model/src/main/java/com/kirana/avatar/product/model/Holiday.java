/**
 * 
 */
package com.kirana.avatar.product.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kirana.avatar.authorization.model.District;
import com.kirana.avatar.authorization.model.State;
import com.kirana.avatar.authorization.model.Taluk;
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
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "state_id", referencedColumnName="id", nullable = true)
	protected State state;
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "district_id", referencedColumnName="id", nullable = true)
	protected District district;
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "taluk_id", referencedColumnName="id", nullable = true)
	protected Taluk taluk;
		
}
