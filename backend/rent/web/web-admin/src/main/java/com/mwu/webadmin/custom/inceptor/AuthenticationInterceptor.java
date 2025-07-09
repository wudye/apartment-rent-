package com.mwu.webadmin.custom.inceptor;

import com.mwu.common.login.LoginUser;
import com.mwu.common.login.LoginUserHolder;
import com.mwu.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 我们约定，前端登录后，后续请求都将JWT，放置于HTTP请求的Header中，其Header的key为`access-token`
        final String token = request.getHeader("access-token");
        final Claims claims = JwtUtil.parseToken(token);
        final Long userId = claims.get("userId", Long.class);
        final String userName = claims.get("username", String.class);
        LoginUserHolder.setLoginUser(LoginUser
                .builder()
                .userId(userId)
                .username(userName)
                .build());
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}
