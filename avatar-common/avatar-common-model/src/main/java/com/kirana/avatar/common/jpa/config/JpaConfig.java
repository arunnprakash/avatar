package com.kirana.avatar.common.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef="dateTimeProvider")
@EnableJpaRepositories(basePackages = { 
		"com.kirana.avatar" 
})
@EnableTransactionManagement
@Slf4j
public class JpaConfig {

	@Bean
	public AuditingEntityListener createAuditingListener() {
		return new AuditingEntityListener();
	}
	@Bean
	public AuditorAware<String> auditorProvider() {
		log.debug("auditorProvider bean instantiated");
		return new AuditorAwareImpl();
	}

	@Bean
	DateTimeProvider dateTimeProvider() {
		return new AuditingDateTimeProvider();
	}
}