/**
 * 
 */
package com.kirana.avatar.authorization.model;

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
@Table(name = "user_devices")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class UserDevice extends BaseEntity<UserDevice>{

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	protected User user;

	@Column(nullable = false)
	protected String modelName;
	
	@Column(nullable = false)
	protected String modelType;
	
	@Column(nullable = false)
	protected String os;
	
	@Column(nullable = false)
	protected String osVersion;
	
	@Column(nullable = false)
	protected String sdkVersion;
	
	@Column(nullable = false)
	protected String language;
	
	@Column(nullable = false)
	protected String manufacturer;
	
	@Column(nullable = false)
	protected String uuid;
	
	@Column(nullable = false)
	protected String screenScale;
	
	@Column(nullable = false)
	protected String screenWidth;
	
	@Column(nullable = false)
	protected String screenHeight;
	
	@Column(nullable = false)
	protected boolean loggedIn;
	
}
