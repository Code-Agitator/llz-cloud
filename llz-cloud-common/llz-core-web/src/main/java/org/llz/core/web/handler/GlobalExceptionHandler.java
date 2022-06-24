package org.llz.core.web.handler;

import org.llz.common.constant.ResultConst;
import org.llz.core.web.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handle404(NoHandlerFoundException e) {
        return Result.fail(ResultConst.NOT_FOUND);
    }
}
