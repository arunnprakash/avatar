/**
 * 
 */
package com.kirana.avatar.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

	@Column(nullable = false)
	protected String modelName;
	
	@Column(nullable = false)
	protected String manufacturer;
	
	@Column(nullable = false)
	protected String imeiNumber;
	
	@Column(nullable = false)
	protected boolean loggedIn;
	
}
