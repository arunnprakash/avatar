/**
 * 
 */
package com.kirana.avatar.master.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.kirana.avatar.common.jpa.entity.LocaleEntity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author __Telmila__
 *
 */

@Entity
@Table(name = "genders")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode(callSuper=true)
@EntityListeners(AuditingEntityListener.class)
public class Gender extends LocaleEntity<Gender>{

}
