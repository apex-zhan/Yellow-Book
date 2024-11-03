package com.litblue.starter.advice;


import com.litblue.starter.core.AjaxResult;
import exception.BizIllegalException;
import exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {
    /*
      全局异常处理，没有指定异常的类型，不管什么异常均可以捕获
       */
    @ExceptionHandler(UnauthorizedException.class)
    /*如果不加，则会导致无法进行异常处理*/
    @ResponseBody
    public AjaxResult error(UnauthorizedException e) {
        e.printStackTrace();
        return new AjaxResult(e.getMessage(), "401");
    }

    @ExceptionHandler(BizIllegalException.class)
    /*如果不加，则会导致无法进行异常处理*/
    @ResponseBody
    public AjaxResult error(BizIllegalException e) {
        e.printStackTrace();
        return new AjaxResult(e.getMessage(), "500");
    }
}