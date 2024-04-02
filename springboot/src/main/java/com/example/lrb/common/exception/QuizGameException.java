package com.example.lrb.common.exception;

import com.example.lrb.vo.JsonResult;
import lombok.Data;

@Data
public class QuizGameException extends RuntimeException {

    private JsonResult<Object> jsonResult;

    public QuizGameException(JsonResult<Object> jsonResult) {
        super(jsonResult.getMessage());
        this.jsonResult = jsonResult;
    }

}
