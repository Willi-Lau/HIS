package com.lwy.demo.exception;


import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

    //User 传进来在数据库找不到
    @ExceptionHandler(value=NullPointerException.class)
    @ResponseBody
    private String exceptionHandlerNullPinter(NullPointerException e){


        System.out.println("NullPointerException异常！！！");

        return "no";

    }
    @ExceptionHandler(value= TooManyResultsException.class)
    @ResponseBody
    private String exceptionHandlerTooManyResults(TooManyResultsException e){


        System.out.println("TooManyResultsException异常！！！");

        return "no";

    }


}
