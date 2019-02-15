/**
 * 
 */
package com.kirana.avatar.product.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kirana.avatar.product.model.Asset;
import com.kirana.avatar.common.jpa.entity.LocaleEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

/**
 * @author __Telmila__
 *
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Product extends LocaleEntity<Product>{
	
	@Column(nullable = false)
	protected String productCode;

	@ManyToMany
	@JoinTable(name = "product_assets", joinColumns={@JoinColumn(name="product_id", referencedColumnName="id", unique=false)}, 
			inverseJoinColumns={@JoinColumn(name="asset_id", referencedColumnName="id", unique=false)})
	protected List<Asset> assets;

	@OneToMany(targetEntity=ProductRegion.class, mappedBy="product")
    private List<ProductRegion> regions;
}
