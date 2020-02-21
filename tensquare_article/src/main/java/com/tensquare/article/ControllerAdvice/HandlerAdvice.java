package com.tensquare.article.ControllerAdvice;


import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class HandlerAdvice {



    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result function1(Exception e){
        System.err.println(e);
        return new Result(false, StatusCode.ERROR,"发生异常",e);
    }
}
