package com.kirana.avatar.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.kirana.avatar.gateway.config.DefaultFallbackProvider;
import com.kirana.avatar.gateway.filters.ErrorFilter;
import com.kirana.avatar.gateway.filters.PostFilter;
import com.kirana.avatar.gateway.filters.PreFilter;
import com.kirana.avatar.gateway.filters.RouteFilter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author __ArunPrakash__
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableSwagger2
@EnableCircuitBreaker
public class AvatarGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvatarGatewayApplication.class, args);
	}
	@Configuration
	public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.httpBasic().disable();
		}
	}
	@Bean
	public DefaultFallbackProvider defaultFallbackProvider() {
		return new DefaultFallbackProvider();
	}

/*	@Bean
	public LocationRewriteFilter locationRewriteFilter() {
		return new LocationRewriteFilter();
	}*/

	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		return ServerCodecConfigurer.create();
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}

	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
