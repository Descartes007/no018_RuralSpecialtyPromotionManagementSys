package com.example.lrb.common.exception;

import com.example.lrb.vo.JsonResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuizGameException.class)
    public JsonResult<Object> onlineHospitalException(QuizGameException e) {
        e.printStackTrace();
        return e.getJsonResult();
    }

    @ExceptionHandler(Exception.class)
    public JsonResult<Object> exception(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return JsonResult.badRequest("不支持此请求方法");
        }
        e.printStackTrace();
        return JsonResult.interServerError();
    }

}
