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
package com.kirana.avatar.common.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author __ArunPrakash__
 *
 */
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserInfo extends User {

	public UserInfo(String username, String password, String mobileNumber, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.mobileNumber = mobileNumber;
	}
	public UserInfo(String username, String password, String mobileNumber,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, mobileNumber, true, true, true, true, authorities);
	}

	private String userToken;
	
	private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Creates a UserBuilder
	 *
	 * @return the UserBuilder
	 */
	public static UserInfoBuilder create() {
		return new UserInfoBuilder();
	}
	public static UserInfoBuilder create(UserInfo userInfo) {
		return new UserInfoBuilder(userInfo);
	}
	public String getUserToken() {
		return userToken;
	}

	/**
	 * Builds the user to be added. At minimum the username, password, and authorities
	 * should provided. The remaining attributes have reasonable defaults.
	 */
	public static class UserInfoBuilder {
		private String username;
		private String password;
		private String mobileNumber;
		private List<GrantedAuthority> authorities;
		private boolean accountExpired;
		private boolean accountLocked;
		private boolean credentialsExpired;
		private boolean disabled;
		private Function<String, String> passwordEncoder = password -> password;
		private UserInfo userInfo;
		/**
		 * Creates a new instance
		 */
		private UserInfoBuilder() {
		}

		private UserInfoBuilder(UserInfo userInfo) {
			this.userInfo = userInfo;
		}

		/**
		 * Populates the username. This attribute is required.
		 *
		 * @param username the username. Cannot be null.
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder username(String username) {
			Assert.notNull(username, "username cannot be null");
			this.username = username;
			return this;
		}

		/**
		 * Populates the password. This attribute is required.
		 *
		 * @param password the password. Cannot be null.
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder password(String password) {
			Assert.notNull(password, "password cannot be null");
			this.password = password;
			return this;
		}

		/**
		 * Populates the password. This attribute is required.
		 *
		 * @param password the password. Cannot be null.
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder mobileNumber(String mobileNumber) {
			Assert.notNull(mobileNumber, "mobileNumber cannot be null");
			if (null != userInfo) {
				this.userInfo.mobileNumber = mobileNumber;
				return this;
			}
			this.mobileNumber = mobileNumber;
			return this;
		}
		/**
		 * Populates the password. This attribute is required.
		 *
		 * @param password the password. Cannot be null.
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder userToken(String userToken) {
			Assert.notNull(userToken, "userToken cannot be null");
			if (null != userInfo) {
				this.userInfo.userToken = userToken;
				return this;
			}
			return this;
		}
		/**
		 * Encodes the current password (if non-null) and any future passwords supplied
		 * to {@link #password(String)}.
		 *
		 * @param encoder the encoder to use
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder passwordEncoder(Function<String, String> encoder) {
			Assert.notNull(encoder, "encoder cannot be null");
			this.passwordEncoder = encoder;
			return this;
		}

		/**
		 * Populates the roles. This method is a shortcut for calling
		 * {@link #authorities(String...)}, but automatically prefixes each entry with
		 * "ROLE_". This means the following:
		 *
		 * <code>
		 *     builder.roles("USER","ADMIN");
		 * </code>
		 *
		 * is equivalent to
		 *
		 * <code>
		 *     builder.authorities("ROLE_USER","ROLE_ADMIN");
		 * </code>
		 *
		 * <p>
		 * This attribute is required, but can also be populated with
		 * {@link #authorities(String...)}.
		 * </p>
		 *
		 * @param roles the roles for this user (i.e. USER, ADMIN, etc). Cannot be null,
		 * contain null values or start with "ROLE_"
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder roles(String... roles) {
			List<GrantedAuthority> authorities = new ArrayList<>(
					roles.length);
			for (String role : roles) {
				Assert.isTrue(!role.startsWith("ROLE_"), () -> role
						+ " cannot start with ROLE_ (it is automatically added)");
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
			return authorities(authorities);
		}

		/**
		 * Populates the authorities. This attribute is required.
		 *
		 * @param authorities the authorities for this user. Cannot be null, or contain
		 * null values
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 * @see #roles(String...)
		 */
		public UserInfoBuilder authorities(GrantedAuthority... authorities) {
			return authorities(Arrays.asList(authorities));
		}

		/**
		 * Populates the authorities. This attribute is required.
		 *
		 * @param authorities the authorities for this user. Cannot be null, or contain
		 * null values
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 * @see #roles(String...)
		 */
		public UserInfoBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = new ArrayList<>(authorities);
			return this;
		}

		/**
		 * Populates the authorities. This attribute is required.
		 *
		 * @param authorities the authorities for this user (i.e. ROLE_USER, ROLE_ADMIN,
		 * etc). Cannot be null, or contain null values
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 * @see #roles(String...)
		 */
		public UserInfoBuilder authorities(String... authorities) {
			return authorities(AuthorityUtils.createAuthorityList(authorities));
		}

		/**
		 * Defines if the account is expired or not. Default is false.
		 *
		 * @param accountExpired true if the account is expired, false otherwise
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder accountExpired(boolean accountExpired) {
			this.accountExpired = accountExpired;
			return this;
		}

		/**
		 * Defines if the account is locked or not. Default is false.
		 *
		 * @param accountLocked true if the account is locked, false otherwise
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder accountLocked(boolean accountLocked) {
			this.accountLocked = accountLocked;
			return this;
		}

		/**
		 * Defines if the credentials are expired or not. Default is false.
		 *
		 * @param credentialsExpired true if the credentials are expired, false otherwise
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder credentialsExpired(boolean credentialsExpired) {
			this.credentialsExpired = credentialsExpired;
			return this;
		}

		/**
		 * Defines if the account is disabled or not. Default is false.
		 *
		 * @param disabled true if the account is disabled, false otherwise
		 * @return the {@link UserInfoBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserInfoBuilder disabled(boolean disabled) {
			this.disabled = disabled;
			return this;
		}

		public UserInfo build() {
			if (null != userInfo) {
				return userInfo;
			}
			String encodedPassword = this.passwordEncoder.apply(password);
			return new UserInfo(username, encodedPassword, mobileNumber, !disabled, !accountExpired,
					!credentialsExpired, !accountLocked, authorities);
		}
	}
}
