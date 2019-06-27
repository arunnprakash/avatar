package com.kirana.avatar.finance.service.impl;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kirana.avatar.authorization.dto.LoginRequest;
import com.kirana.avatar.authorization.dto.LoginResponse;
import com.kirana.avatar.authorization.dto.RoleDTO;
import com.kirana.avatar.authorization.feign.UserClient;
import com.kirana.avatar.common.dto.UserInfo;
import com.kirana.avatar.common.exception.ApiException;
import com.kirana.avatar.finance.model.Notification;
import com.kirana.avatar.finance.model.NotificationType;
import com.kirana.avatar.finance.repositories.NotificationRepository;
import com.kirana.avatar.finance.service.NotificationSender;
import com.kirana.avatar.finance.specifications.NotificationSpecification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NotificationScheduledTasks {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private NotificationSpecification notificationSpecification;

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private UserClient userClient;

	@Scheduled(fixedRate = 10000, initialDelay = 30000)
	@Transactional
	public void scheduleTaskWithFixedRate() {
		
		Specification<Notification> notificationSpec = Specification.where(notificationSpecification.hasDeleted(false));
		notificationSpec = notificationSpec.and(notificationSpecification.hasNotificationStatusNew());
		notificationRepository
			.findAll(notificationSpec)
			.stream()
			.findFirst()
			.ifPresent(t -> {
				try {
					setAuthentication();
					processNotification(t);
				} catch (Exception e) {
					log.error("Error while processing notification " + t, e);
				}
			});
	}

	private void setAuthentication() {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserNameOrMobileNumber("admin");
		loginRequest.setPassword("avatar");
		LoginResponse loginResponse = userClient.login(loginRequest);
		String roles = loginResponse.getUserDTO().getRoles().stream().map(RoleDTO::getRoleName).collect(joining(","));
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		UserInfo userInfo = UserInfo.create()
				.password(loginRequest.getUserNameOrMobileNumber())
				.mobileNumber(loginRequest.getUserNameOrMobileNumber())
				.username(loginRequest.getUserNameOrMobileNumber())
				.userToken(loginResponse.getAccessToken())
				.authorities(grantedAuthorities)
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
		Authentication auth = new UsernamePasswordAuthenticationToken(userInfo, null, grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private void processNotification(Notification notification) throws Exception {
		log.info("Notification Process Started - {}", dateTimeFormatter.format(LocalDateTime.now()));
		NotificationType notificationType = notification.getNotificationType();
		String serviceName = notificationType.getType();
		NotificationSender notificationSender = (NotificationSender)applicationContext.getBean(serviceName);
		if (nonNull(notificationSender)) {
			notificationSender.send(notification);
		} else {
			throw ApiException.implementationNotFound();
		}
		log.info("Notification Process Completed - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

}