package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class XPFTooManyRequestsException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.TOO_MANY_REQUESTS;

    public XPFTooManyRequestsException(String errorHuman) {
        super(buildErrorCode(STATUS), combineErrorHuman(errorHuman, "请求数量过多"));
    }

    public XPFTooManyRequestsException() {
        this(null);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
