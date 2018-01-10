package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

/**
 * 依赖的外部资源不稳定的异常
 */
public class XPFServiceUnavailableException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.SERVICE_UNAVAILABLE;

    public XPFServiceUnavailableException(String errorHuman) {
        super(buildErrorCode(STATUS), combineErrorHuman(errorHuman, "服务器资源暂时不可用"));
    }

    public XPFServiceUnavailableException() {
        this(null);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
