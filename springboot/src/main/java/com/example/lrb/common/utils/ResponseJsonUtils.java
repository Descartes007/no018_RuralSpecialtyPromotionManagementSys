package com.example.lrb.common.utils;

import com.example.lrb.vo.JsonResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseJsonUtils {

    public static void writeJson(HttpServletResponse response, JsonResult<Object> result) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtils.serialize(result));
        writer.flush();
    }

    public static void setToken(HttpServletResponse response, String token) {
        response.setHeader("Access-Control-Expose-Headers", "SET_TOKEN");
        response.setHeader("SET_TOKEN", token);
    }

    public static void loginSuccess(HttpServletResponse response, Object data) throws IOException {
        writeJson(response, JsonResult.ok("登陆成功", data));
    }


    public static void logoutSuccess(HttpServletResponse response) throws IOException {
        writeJson(response, JsonResult.ok("退出成功"));
    }


    public static void unauthorized(HttpServletResponse response, Object data) throws IOException {
        writeJson(response, JsonResult.unauthorized("请登录!", data));
    }

    public static void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, null);
    }

    public static void forbidden(HttpServletResponse response, Object data) throws IOException {
        writeJson(response, JsonResult.forbidden("用户无权限!", data));
    }

    public static void forbidden(HttpServletResponse response) throws IOException {
        forbidden(response, null);
    }


}
