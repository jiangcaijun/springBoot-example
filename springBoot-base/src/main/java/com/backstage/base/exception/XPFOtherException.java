package com.backstage.base.exception;

import org.springframework.http.HttpStatus;

public class XPFOtherException extends XPFBaseException {

    private HttpStatus status;

    public XPFOtherException(HttpStatus status, String errorHuman) {
        super(buildErrorCode(status), errorHuman);
        this.status = status;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return status;
    }
}
