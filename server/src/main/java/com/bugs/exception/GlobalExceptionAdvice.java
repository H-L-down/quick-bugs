package com.bugs.exception;

import com.bugs.controller.result.StatusCode;
import com.bugs.controller.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(ServiceException.class)
    public Result doCatchServiceException(ServiceException ex) {
        return Result.createResult(StatusCode.ERROR, null, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doCatchUnexpectedException(Exception ex) {
        // 未预期的异常
        System.out.println(ex);
        return Result.createResult(StatusCode.ERROR, null, "发送了未知的错误");
    }
}
