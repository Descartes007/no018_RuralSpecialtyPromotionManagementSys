package com.example.lrb.controller;

import com.example.lrb.common.utils.JsonUtils;
import com.example.lrb.common.utils.JwtUtils;
import com.example.lrb.common.utils.LocalDateTimeUtil;
import com.example.lrb.common.utils.ResponseJsonUtils;
import com.example.lrb.pojo.User;
import com.example.lrb.vo.JsonResult;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 权限拦截器
 */
@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 校验用户是否存在
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            // 从session获取用户信息
            Object user = request.getSession().getAttribute("user");
            if (user == null && request.getRequestURI().contains("/admin")) {
                request.setAttribute("msg", "请登录");
                request.getRequestDispatcher("/admin/login").forward(request, response);
                return false;
            } else if (user != null) {
                request.setAttribute("user", user);
                return true;
            }

            ResponseJsonUtils.unauthorized(response);
            return false;
        }

        Claims claims = null;
        try {
            claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("token解析失败");
            ResponseJsonUtils.unauthorized(response, JsonResult.badRequest("解析失败"));
            return false;
        }
        User user = JsonUtils.parse(JsonUtils.serialize(claims.get("data")), User.class);
        request.setAttribute("user", user);

        // 是否更新token 15分钟内
        if (isUpdateToken(claims)) {
            String newToken = JwtUtils.generateToken(user.getId().toString(), user);
            ResponseJsonUtils.setToken(response, newToken);
        }

        return true;
    }

    private boolean isUpdateToken(Claims claims) {
        LocalDateTime expirationDate = LocalDateTimeUtil.dateConvertToLocalDateTime(claims.getExpiration());
        LocalDateTime currentDate = LocalDateTime.now();
        long expirationMinute = Duration.between(currentDate, expirationDate).toMinutes();
        return expirationMinute < 5;
    }

}
