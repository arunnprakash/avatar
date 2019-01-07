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
package com.kirana.avatar.common.exception;

import com.kirana.avatar.common.enums.ErrorCode;

/**
 * @author __ArunPrakash__
 *
 */
public class ApiException extends RuntimeException {
	private ErrorCode errorCode;

	public ApiException(ErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public static RuntimeException resourceNotFound() {
		return new ApiException(ErrorCode.NOT_FOUND);
	}
	
	public static RuntimeException resourceAlreadyExist() {
		return new ApiException(ErrorCode.ALREADY_EXIST);
	}
	
	public static RuntimeException implementationNotFound() {
		return new ApiException(ErrorCode.ALREADY_EXIST);
	}
}
