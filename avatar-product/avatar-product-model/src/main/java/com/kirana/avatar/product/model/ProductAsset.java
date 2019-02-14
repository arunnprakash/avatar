/**
 * 
 */
package com.kirana.avatar.product.model;

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
@Table(name = "product_assets")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class ProductAsset extends BaseEntity<ProductAsset>{
	
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "product_id", referencedColumnName="id", nullable = false)
	protected Product product;
	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "asset_id", referencedColumnName="id", nullable = false)
	protected Asset asset;

}
