package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

/**
 * 业务异常
 */
public class BusinessException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.BAD_REQUEST;

	public BusinessException(String message){
	    super(buildErrorCode(STATUS), message);
	}

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
