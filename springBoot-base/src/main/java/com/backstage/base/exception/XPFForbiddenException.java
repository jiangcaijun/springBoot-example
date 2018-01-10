package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class XPFForbiddenException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.FORBIDDEN;

    public XPFForbiddenException() {
        this(null);
    }

    public XPFForbiddenException(String errorHuman) {
        super(buildErrorCode(STATUS), combineErrorHuman(errorHuman, "没有权限访问"));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
