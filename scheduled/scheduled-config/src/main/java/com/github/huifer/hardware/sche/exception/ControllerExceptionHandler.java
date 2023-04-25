package com.github.huifer.hardware.sche.exception;

import com.github.huifer.hardware.common.base.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 统一异常处理、数据预处理
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultResponse validExceptionHandler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.error("MethodArgumentNotValidException发生参数异常:{}", message);
        return ResultResponse.error(ResultResponse.ERROR_CODE, message);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultResponse exceptionHandler(Exception e) {
        log.error("Exception发生参数异常:{}", e);
        return ResultResponse.error(ResultResponse.ERROR_CODE, e.getMessage());
    }
}