package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class XPFJsonTransformException extends XPFBaseException {

    private final static HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public XPFJsonTransformException(String errorHuman) {
        super(buildErrorCode(STATUS), combineErrorHuman(errorHuman, "JSON转换错误"));
    }

    public XPFJsonTransformException() {
        this(null);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return STATUS;
    }
}
