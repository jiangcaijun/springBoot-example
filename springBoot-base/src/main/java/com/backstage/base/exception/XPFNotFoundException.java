package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class XPFNotFoundException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public XPFNotFoundException(String errorHuman) {
        super(buildErrorCode(STATUS), combineErrorHuman(errorHuman, "资源不存在"));
    }

    public XPFNotFoundException() {
        this(null);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
