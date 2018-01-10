package com.backstage.base.exception;

import com.backstage.base.response.XPFExceptionResponse;
import org.springframework.http.HttpStatus;

public abstract class XPFBaseException extends Exception {

    protected String errorCode;

    protected String errorHuman;

    protected Object reason;

    public XPFBaseException(String errorCode, String errorHuman) {
        this(errorCode, errorHuman, null);
    }

    public XPFBaseException(String errorCode, String errorHuman, Throwable cause) {
        super(errorCode + " " + errorHuman, cause);
        this.errorCode = errorCode;
        this.errorHuman = errorHuman;
    }

    public abstract HttpStatus getHttpStatus();

    /**
     * 转换成错误的回复机制
     */
    public XPFExceptionResponse toExceptionResponse() {
        return new XPFExceptionResponse(this.errorCode, this.errorHuman, this.reason);
    }

    public static String combineErrorHuman(String errorHuman, String defaultVal) {
        return (errorHuman == null) ? defaultVal : errorHuman;
    }

    public static String buildErrorCode(HttpStatus status) {
        return status.getReasonPhrase().toLowerCase().replace(' ', '_');
    }
}
