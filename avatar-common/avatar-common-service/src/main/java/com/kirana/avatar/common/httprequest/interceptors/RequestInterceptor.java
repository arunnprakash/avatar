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
package com.kirana.avatar.common.httprequest.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kirana.avatar.common.dto.UserInfo;

/**
 * @author __ArunPrakash__
 *
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
	private static Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);
	 @Override
	 public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object object) throws Exception {
		 String requestURI = request.getRequestURI();
		LOGGER.debug("In preHandle we are Intercepting the Request :: " + requestURI);
		LOGGER.debug("____________________________________________");
		request.setAttribute("userInfoDTO", getUserInfoDTO(request));
		String xForwardedFor = request.getHeader("X-Forwarded-For");
		LOGGER.debug("X-Forwarded-For {}", xForwardedFor);
		LOGGER.debug("UserInfo attached as request attribute");
		LOGGER.debug("____________________________________________");
		return true;
	 }

	 @Override
	 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object, ModelAndView model)
			throws Exception {
		 String requestURI = request.getRequestURI();
		LOGGER.debug("_________________________________________");
		LOGGER.debug("In postHandle request processing " + "completed by @RestController :: " + requestURI);
		LOGGER.debug("_________________________________________");
	 }

	 @Override
	 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object object, Exception arg3)
			throws Exception {
		 String requestURI = request.getRequestURI();
		LOGGER.debug("________________________________________");
		LOGGER.debug("In afterCompletion Request Completed :: " + requestURI);
		LOGGER.debug("________________________________________");
	 }
	 
	private UserInfo getUserInfoDTO(HttpServletRequest request) {
		UserInfo userInfo = null;//new UserInfo();
		return userInfo;
	}
}
