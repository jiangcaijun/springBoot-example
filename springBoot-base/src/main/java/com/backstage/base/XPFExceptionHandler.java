package com.backstage.base;

import com.backstage.base.exception.*;
import com.backstage.base.response.XPFBaseResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * Created by shidayang on 2017/8/8.
 */
@RestControllerAdvice
public class XPFExceptionHandler {

    private static final Logger LOG = Logger.getLogger(XPFExceptionHandler.class);

    /**
     * 可以确定的异常分类
     * @param ex 异常
     * @param response 回复对象
     * @return
     */
    @ExceptionHandler(value = XPFBaseException.class)
    public XPFBaseResponse onXPFBaseException(
            XPFBaseException ex, HttpServletResponse response){
        LOG.warn(ex.getMessage(), ex);
        response.setStatus(ex.getHttpStatus().value());
        return ex.toExceptionResponse();
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public XPFBaseResponse onHttpRequestMethodNotSupportedException(
                HttpServletRequest request, HttpServletResponse response,HttpRequestMethodNotSupportedException e){
                LOG.warn(e.getMessage(),e);
        return onXPFBaseException(new XPFOtherException(HttpStatus.METHOD_NOT_ALLOWED,
                "请求 " + request.getRequestURI() +" 的方法 '" + request.getMethod() +"' 不支持"), response);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public XPFBaseResponse exceptionHandler(
            HttpMessageNotReadableException ex, HttpServletResponse response){
        LOG.error(ex.getMessage(),ex);
        return onXPFBaseException(new XPFBadRequestException("提交的内容不存在或者格式不正确"), response);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public XPFBaseResponse onAccessDeniedException(
            AccessDeniedException ex, HttpServletResponse response){
        return onXPFBaseException(new XPFForbiddenException(), response);
    }

    /**
     * 不能确认的异常对象
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public XPFBaseResponse exceptionHandler(
            Exception ex, HttpServletResponse response){
        LOG.error(ex.getMessage(),ex);
        return onXPFBaseException(new XPFServerException("内部错误: " + ex.getMessage()), response);
    }
}
