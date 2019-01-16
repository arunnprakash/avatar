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

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity<User> {
	@Column(nullable = false)
	protected String userName;
	@Column(nullable = false)
	protected String firstName;
	@Column(nullable = false)
	protected String lastName;
	@Column(nullable = false)
	protected String mobileNumber;
	@Column(nullable = true)
	protected ZonedDateTime dob;
	@Column(nullable = false)
	protected String password;
	@Column(nullable = false)
	protected Boolean suspended;
	@Column(nullable = true)
	protected String latitude;
	@Column(nullable = true)
	protected String longitude;

	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "language_id", referencedColumnName="id", nullable = false)
	protected Language preferredLanguage;


	@ManyToOne(optional = false, fetch=FetchType.EAGER)
	@JoinColumn(name = "village_id", referencedColumnName="id", nullable = false)
	protected Village village;

	@ManyToMany(cascade= {CascadeType.MERGE})
	@JoinTable(name = "user_roles", joinColumns={@JoinColumn(name="user_id", referencedColumnName="id", unique=false)}, 
			inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id", unique=false)})
	protected List<Role> roles;

	@ManyToMany
	@JoinTable(name = "user_assets", joinColumns={@JoinColumn(name="user_id", referencedColumnName="id", unique=false)}, 
			inverseJoinColumns={@JoinColumn(name="asset_id", referencedColumnName="id", unique=false)})
	protected List<Asset> assets;

}
