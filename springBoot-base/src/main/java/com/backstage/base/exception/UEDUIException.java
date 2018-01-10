package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

/**
 *
 */
public class UEDUIException extends XPFBaseException {
    private final static HttpStatus STATUS = HttpStatus.BAD_REQUEST;

    public UEDUIException(String message){
        super(buildErrorCode(STATUS), message);
    }

    public UEDUIException(Throwable cause){
        super(buildErrorCode(STATUS), "UED UI异常", cause);
    }

    public UEDUIException(String message, Throwable cause){
        super(buildErrorCode(STATUS), message,cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
