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
package com.kirana.avatar.common.jpa.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author __ArunPrakash__
 *
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@MappedSuperclass
public abstract class BaseEntity<Model> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "native", strategy = "native")
	protected Long id;
	
	@CreatedBy
	@Column(nullable = false)
	protected String createdBy;

	@CreatedDate
	@Column(nullable = false)
	protected ZonedDateTime createdDate;

	@LastModifiedBy
	@Column(nullable = false)
	protected String lastModifiedBy;

	@LastModifiedDate
	@Column(nullable = false)
	protected ZonedDateTime lastModifiedDate;
 
	@Column(nullable = false)
	protected Boolean deleted = false;

	@Version
	protected Long version;

	@SuppressWarnings("unchecked")
	public Model markAsDeleted(){
		this.deleted = true;
		return (Model)this;
	}
}
