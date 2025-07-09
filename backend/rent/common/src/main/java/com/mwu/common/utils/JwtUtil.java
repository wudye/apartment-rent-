package com.mwu.common.utils;

import com.mwu.common.exception.LeaseException;
import com.mwu.common.result.ResultCodeEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static long tokenExpiration = 60 * 60 * 1000L;
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());


    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                .subject("USER_INFO")
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(tokenSignKey)
                .compact();
    }

    public static Claims parseToken(String token) {
        if (token == null || token.isEmpty()) {
            throw  new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            return Jwts
                    .parser()
                    .verifyWith(tokenSignKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
    public static void main(String[] args) {
        // 为方便继续调试其他接口，可以获取一个长期有效的Token，将其配置到Knife4j的全局参数中
        System.out.println(JwtUtil.createToken(1L, "admin"));
    }
}
