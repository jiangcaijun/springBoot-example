package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class XPFServerException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;


    public XPFServerException(String errorHuman) {
        super(buildErrorCode(STATUS), errorHuman);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
