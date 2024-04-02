package com.example.lrb.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    //签名私钥
    private static String KEY = "23984d03-4f16-44e3-845e-1ef93f26ab96";
    //签名的失效时间 24小时
    private static Integer EXPIRATION_HOURS = 24;

    /**
     * 私钥加密token
     *
     * @param username 载荷中的数据
     * @param map      载荷中的数据
     * @return
     * @throws Exception
     */
    public static String generateToken(String username, Map<String, Object> map) {
        return Jwts.builder()
                .claim("userInfo", map)
                .setSubject(username)
                .setExpiration(Date.from(LocalDateTime.now().plusHours(EXPIRATION_HOURS).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public static String generateToken(String username, Object data) {
        return Jwts.builder()
                .claim("data", data)
                .setSubject(username)
                .setExpiration(Date.from(LocalDateTime.now().plusHours(EXPIRATION_HOURS).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }


    /**
     * 解析token字符串
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    /**
     * 获取jwt失效时间
     */
    public static Date getExpiration(String token) throws Exception {
        return parseToken(token).getExpiration();
    }

    public static Object getData(Claims claims) throws Exception {
        return claims.get("data");
    }
}