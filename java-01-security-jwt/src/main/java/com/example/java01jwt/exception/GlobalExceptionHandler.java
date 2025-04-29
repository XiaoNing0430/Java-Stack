package com.example.java01jwt.exception;

import com.example.java01jwt.model.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public Result<Void> handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        String message = e.getMessage();
        return Result.error(message);
    }

    @ExceptionHandler(value = Exception.class)
    public Result<Void> handleException(Exception e) {
        String message = e.getMessage();
        return Result.error(message);
    }

}