package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

/**
 * 无权限访问异常
 */
public class XPFUnauthorizedException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public XPFUnauthorizedException(String errorHuman) {
        super(buildErrorCode(STATUS), combineErrorHuman(errorHuman, "需要认证才能访问"));
    }

    public XPFUnauthorizedException() {
        this(null);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
