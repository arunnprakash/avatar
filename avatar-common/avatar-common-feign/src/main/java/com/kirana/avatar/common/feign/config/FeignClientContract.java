/*******************************************************************************
 *
 * Copyright (c) 2019 Granatech Limited
 *
 * All information contained herein is, and remains the property of Granatech
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Granatech and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from 
 * Granatech Limited
 *
 *******************************************************************************/
package com.kirana.avatar.common.feign.config;

import static feign.Util.checkState;
import static feign.Util.emptyToNull;
import static org.springframework.core.annotation.AnnotationUtils.synthesizeAnnotation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import feign.MethodMetadata;
import feign.Util;

/**
 * @author __ArunPrakash__
 *
 */
public class FeignClientContract extends SpringMvcContract {
	private ResourceLoader resourceLoader;

	@Override
	public List<MethodMetadata> parseAndValidatateMetadata(final Class<?> targetType) {
		checkState(targetType.getTypeParameters().length == 0, "Parameterized types unsupported: %s", targetType.getSimpleName());
		final Map<String, MethodMetadata> result = new LinkedHashMap<>();
		for (final Method method : targetType.getMethods()) {
			if (method.getDeclaringClass() == Object.class || (method.getModifiers() & Modifier.STATIC) != 0 || Util.isDefault(method)) {
				continue;
			}
			final MethodMetadata metadata = this.parseAndValidateMetadata(targetType, method);
			checkState(!result.containsKey(metadata.configKey()), "Overrides unsupported: %s", metadata.configKey());
			result.put(metadata.configKey(), metadata);
		}
		return new ArrayList<>(result.values());
	}

	@Override
	public MethodMetadata parseAndValidateMetadata(final Class<?> targetType, final Method method) {
		final MethodMetadata methodMetadata = super.parseAndValidateMetadata(targetType, method);

		final LinkedList<Class<?>> classHierarchy = new LinkedList<>();
		classHierarchy.add(targetType);
		this.findClass(targetType, method.getDeclaringClass(), classHierarchy);
		classHierarchy.stream()
		        .map(this::processPathValue)
		        .filter(Optional::isPresent)
		        .map(Optional::get)
		        .findFirst()
		        .ifPresent((path) -> methodMetadata.template().insert(0, path));
		return methodMetadata;
	}

	private Optional<String> processPathValue(final Class<?> clz) {
		Optional<String> result = Optional.empty();
		final RequestMapping classAnnotation = clz.getAnnotation(RequestMapping.class);
		if (classAnnotation != null) {
			final RequestMapping synthesizeAnnotation = synthesizeAnnotation(classAnnotation, clz);
			// Prepend path from class annotation if specified
			if (synthesizeAnnotation.value().length > 0) {
				String pathValue = emptyToNull(synthesizeAnnotation.value()[0]);
				pathValue = this.resolveValue(pathValue);
				if (!pathValue.startsWith("/")) {
					pathValue = "/" + pathValue;
				}
				result = Optional.of(pathValue);
			}
		}
		return result;
	}

	private String resolveValue(final String value) {
		if (StringUtils.hasText(value) && this.resourceLoader instanceof ConfigurableApplicationContext) {
			return ((ConfigurableApplicationContext) this.resourceLoader).getEnvironment().resolvePlaceholders(value);
		}
		return value;
	}

	@Override
	protected void processAnnotationOnClass(final MethodMetadata data, final Class<?> clz) {
		// skip this step
	}

	private boolean findClass(final Class<?> currentClass, final Class<?> searchClass,
	        final LinkedList<Class<?>> classHierarchy) {
		if (currentClass == searchClass) {
			return true;
		}
		final Class<?>[] interfaces = currentClass.getInterfaces();
		for (final Class<?> currentInterface : interfaces) {
			classHierarchy.add(currentInterface);
			final boolean findClass = this.findClass(currentInterface, searchClass, classHierarchy);
			if (findClass) {
				return true;
			}
			classHierarchy.removeLast();
		}
		return false;
	}

	@Override
	public void setResourceLoader(final ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
		super.setResourceLoader(resourceLoader);
	}
}
