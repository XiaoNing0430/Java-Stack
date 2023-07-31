package edu.xiao.java01jwt.exception;

import edu.xiao.java01jwt.constant.ResponseCodeEnum;
import edu.xiao.java01jwt.model.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public Result<Void> handleException(SQLSyntaxErrorException e) {
        String message = e.getMessage();
        return Result.error(message);
    }

}