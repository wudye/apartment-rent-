package com.mwu.common.exception;

import com.mwu.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(LeaseException.class)
    @ResponseBody
    public Result error(LeaseException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMessage());
    }

}
