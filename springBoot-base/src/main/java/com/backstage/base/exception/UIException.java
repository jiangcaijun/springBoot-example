package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class UIException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.BAD_REQUEST;

	public UIException(String message){
		super(buildErrorCode(STATUS),message);
	}
	
	public UIException(Throwable cause){
		super(buildErrorCode(STATUS), "UI异常", cause);
	}
	
	public UIException(String message, Throwable cause){
		super(buildErrorCode(STATUS), message,cause);
	}

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }

}
