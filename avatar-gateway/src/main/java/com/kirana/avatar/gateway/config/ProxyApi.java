package com.kirana.avatar.gateway.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * SpringFox Swagger still does not provide support for Spring WebFlux. we have to use Spring Cloud Netflix Zuul as a gateway SwaggerResourcesProvider
 * @author __ArunPrakash__
 *
 */
@Configuration
public class ProxyApi {

	@Autowired
	private ZuulProperties properties;

	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider() {
		return () -> {
			return properties.getRoutes()
					.values()
					.stream()
					.map(this::createResource)
					.collect(Collectors.toList());
		};
	}

	private SwaggerResource createResource(ZuulRoute route) {
		return createResource(route.getServiceId(), route.getId(), "2.0");
	}

	private SwaggerResource createResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation("/" + location + "/v2/api-docs");
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}