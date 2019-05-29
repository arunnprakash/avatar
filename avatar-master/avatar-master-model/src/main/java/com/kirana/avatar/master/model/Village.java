/**
 * 
 */
package com.kirana.avatar.master.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kirana.avatar.common.jpa.entity.LocaleEntity;

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
@Table(name = "villages")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Village extends LocaleEntity<Village>{

	@Column(nullable = false)
	protected String villageCode;
	
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	@JoinColumn(name="taluk_id", referencedColumnName="id")
	private Taluk taluk;
	
}
