package com.kirana.avatar.authorization.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kirana.avatar.authorization.model.Role;
import com.kirana.avatar.authorization.model.User;
import com.kirana.avatar.authorization.repositories.UserRepository;
import com.kirana.avatar.common.dto.UserInfo;

import lombok.extern.slf4j.Slf4j;
/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userNameOrMobileNumber) throws UsernameNotFoundException {
		User user = userRepository
				.findByUserNameOrMobileNumber(userNameOrMobileNumber, userNameOrMobileNumber)
				.orElseThrow(() -> 
					new UsernameNotFoundException("User not found with username or mobilenumber : " + userNameOrMobileNumber)
				);
		log.debug("User Found with username or mobilenumber {}", userNameOrMobileNumber);
		// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
		// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
		String roles = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.joining(","));
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		
		// The "UserInfo" class is provided by common and represents a model class for user to be returned by UserDetailsService
		// And used by auth manager to verify and check user authentication.
		return UserInfo.create()
				.password(user.getPassword())
				.username(user.getUserName())
				.mobileNumber(user.getMobileNumber())
				.authorities(grantedAuthorities)
				.build();
		
	}
}