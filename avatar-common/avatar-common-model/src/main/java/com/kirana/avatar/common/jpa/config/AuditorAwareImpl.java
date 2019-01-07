package com.kirana.avatar.common.jpa.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kirana.avatar.common.dto.UserInfo;

import lombok.extern.slf4j.Slf4j;
/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		log.debug("Getting Current Auditor");
		return Optional.ofNullable(getCurrentUser().getUsername());
	}

	private UserInfo getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		UserInfo userInfo = (UserInfo) authentication.getPrincipal();
		log.debug("Got Authentication Principal {}", userInfo);
		return userInfo;
	}
}