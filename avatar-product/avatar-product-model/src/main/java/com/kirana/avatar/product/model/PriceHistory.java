/**
 * 
 */
package com.kirana.avatar.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "priceHistories")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class PriceHistory extends BaseEntity<PriceHistory>{
	
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "product_id", referencedColumnName="id", nullable = false)
	protected Product productId;
	@Column(nullable = false)
	protected Double price;
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "quality_id", referencedColumnName="id", nullable = false)
	protected Quality qualityId;

}
