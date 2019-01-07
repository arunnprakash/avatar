package com.kirana.avatar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@EnableAutoConfiguration
@EnableEurekaServer
@SpringBootApplication
public class AvatarServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvatarServiceDiscoveryApplication.class, args);
	}
	
	@Configuration
	public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
		}
	}
}
