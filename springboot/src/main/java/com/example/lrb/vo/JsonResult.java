package com.example.lrb.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class JsonResult<T> {

    private int status;
    private String message;
    private T data;
    private Long timestamp = System.currentTimeMillis();

    public JsonResult() {
    }

    public JsonResult(int status) {
        this.status = status;
    }

    public JsonResult(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }


    public JsonResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> JsonResult<T> ok() {
        return new JsonResult<T>(HttpStatus.OK.value());
    }

    public static <T> JsonResult<T> ok(String message) {
        return new JsonResult<T>(HttpStatus.OK.value(), message);
    }

    public static <T> JsonResult<T> ok(String message, T data) {
        return new JsonResult<T>(HttpStatus.OK.value(), message, data);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<T>(HttpStatus.OK.value(), data);
    }


    public static <T> JsonResult<T> error(int status, String message) {
        return new JsonResult<T>(status, message);
    }

    public static <T> JsonResult<T> error(int status, String message, T data) {
        return new JsonResult<T>(status, message, data);
    }

    public static <T> JsonResult<T> forbidden(String message) {
        return new JsonResult<T>(HttpStatus.FORBIDDEN.value(), message);
    }

    public static <T> JsonResult<T> forbidden(String message, T data) {
        return new JsonResult<T>(HttpStatus.FORBIDDEN.value(), message, data);
    }

    public static <T> JsonResult<T> badRequest(String message) {
        return new JsonResult<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    public static <T> JsonResult<T> badRequest(T data) {
        return new JsonResult<T>(HttpStatus.BAD_REQUEST.value(), data);
    }

    public static <T> JsonResult<T> badRequest(String message, T data) {
        return new JsonResult<T>(HttpStatus.BAD_REQUEST.value(), message, data);
    }


    public static <T> JsonResult<T> badRequest() {
        return new JsonResult<T>(HttpStatus.BAD_REQUEST.value());
    }

    public static <T> JsonResult<T> interServerError() {
        return new JsonResult<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统错误，操作失败！");
    }

    public static <T> JsonResult<T> interServerError(String message) {
        return new JsonResult<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static <T> JsonResult<T> unauthorized(String message) {
        return new JsonResult<T>(HttpStatus.UNAUTHORIZED.value(), message);
    }

    public static <T> JsonResult<T> unauthorized(String message, T data) {
        return new JsonResult<T>(HttpStatus.UNAUTHORIZED.value(), message, data);
    }

    public static <T> JsonResult<T> unauthorized() {
        return new JsonResult<T>(HttpStatus.UNAUTHORIZED.value(), "请登录！");
    }

    public static <T> JsonResult<T> notFound(String message) {
        return new JsonResult<T>(HttpStatus.NOT_FOUND.value(), message);
    }


    public boolean isSuccess() {
        return this.status == 200;
    }
}
