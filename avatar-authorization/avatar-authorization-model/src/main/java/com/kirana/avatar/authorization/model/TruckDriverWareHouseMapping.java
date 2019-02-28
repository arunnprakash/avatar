/*******************************************************************************
 *
 * Copyright (c) 2019 GranaTech Limited
 *
 * All information contained herein is, and remains the property of GranaTech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to GranaTech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from GranaTech
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.model;

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
 * @author __ArunPrakash__
 *
 */

@Entity
@Table(name = "truckdriver_warehouse_mapping")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class TruckDriverWareHouseMapping extends BaseEntity<TruckDriverWareHouseMapping> {

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "truck_driver_id", referencedColumnName = "id", nullable = false)
	private User truckDriver;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false)
	private WareHouse wareHouse;

}
