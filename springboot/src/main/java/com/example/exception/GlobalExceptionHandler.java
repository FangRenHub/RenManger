package com.example.exception;

import com.example.common.Result;
import com.example.controller.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice("com.example.controller")
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        logger.error("系统异常", e);
        return Result.error("系统异常test");
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public Result customerError(CustomerException e) {
        logger.error("自定义错误", e);
        return Result.error(e.getCode(), e.getMsg());
    }

}
