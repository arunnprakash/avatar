/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.authorization.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kirana.avatar.common.jpa.entity.BaseEntity;
import com.kirana.avatar.common.jwt.config.JwtConfig;

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
	@Column(nullable = false)
	protected String password;
	@Column(nullable = false)
	protected Boolean suspended;
	@Column(nullable = true)
	protected String latitude;
	@Column(nullable = true)
	protected String longitude;

	@Transient
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transient
	@Autowired
	private JwtConfig jwtConfig;

	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns={@JoinColumn(name="user_id", referencedColumnName="id", unique=false)}, 
			inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id", unique=false)})
	private List<Role> roles;

	@ManyToMany
	@JoinTable(name = "user_assets", joinColumns={@JoinColumn(name="user_id", referencedColumnName="id", unique=false)}, 
			inverseJoinColumns={@JoinColumn(name="asset_id", referencedColumnName="id", unique=false)})
	private List<Asset> assets;

	@PrePersist
	public void onCreate() {
		this.password = "admin";
		this.suspended = false;
	}
}
