package com.backstage.base.exception;

/**
 * 数据校验异常
 */
public class ValidateException extends BusinessException {
    public ValidateException(String message) {
        super(message);
    }
}
