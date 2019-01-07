/*******************************************************************************
 *
 * Copyright (c) 2018 OLAM Limited
 *
 * All information contained herein is, and remains the property of OLAM
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to OLAM and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from OLAM
 * Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.swagger.config;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  {//extends WebMvcConfigurationSupport

	@Value("${spring.application.name}")
	private String appName;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.kirana")).paths(PathSelectors.any()).build()
				.forCodeGeneration(true)
				.apiInfo(metaData())
				.globalOperationParameters(
						Arrays.asList(getAuthorizationHeaderParam(), getContentTypeHeaderParam()));
	}

	private Parameter getContentTypeHeaderParam() {
		return new ParameterBuilder()
				.name("Content-Type")
				.description("Content Type default is application/json")
				.defaultValue(APPLICATION_JSON_VALUE)
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(true)
				.build();
	}

	private Parameter getAuthorizationHeaderParam() {
		return new ParameterBuilder()
				.name("Authorization")
				.description("Bearer Access Token")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(true)
				.build();
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(appName + " REST API")
				.description("\""+appName+" REST API for OLAM-Direct App offline mode of working \"").version("1.0.0")
				.license("Copyright (c) 2018 OLAM Limited")
				.contact(new Contact("ArunPrakash", "https://www.olamgroup.com/", "arunprakash.b@olamnet.com")).build();
	}

/*	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}*/
}
