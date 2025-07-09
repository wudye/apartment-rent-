package com.mwu.webadmin.controller.login;

import com.mwu.common.login.LoginUserHolder;
import com.mwu.common.result.Result;
import com.mwu.webadmin.services.LoginService;
import com.mwu.webadmin.vo.login.CaptchaVo;
import com.mwu.webadmin.vo.login.LoginVo;
import com.mwu.webadmin.vo.system.SystemUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "获取图形验证码")
    @GetMapping("/login/captcha")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo captcha = loginService.getCaptcha();
        return Result.ok(captcha);
    }


    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = loginService.login(loginVo);
        if (token == null) {
            return Result.fail();
        }
        return Result.ok(token);
    }
    // @Operation(summary = "获取登陆用户个人信息")
    // @GetMapping("info")
    // public Result<SystemUserInfoVo> info(
    //         @RequestParam("assess-token")
    //                 String token) {
    //     // 问题痛点：代码的逻辑没有任何问题，但是这样做，JWT会被重复解析两次（一次在拦截器中，一次在该方法中）。
    //     //          而且每个请求都要这么进行解析太繁琐麻烦了
    //     // 解决思路：ThreadLocal应运而生
    //     // 解决方案：为避免重复解析，通常会在拦截器将Token解析完毕后，将结果保存至ThreadLocal中，这样一来，我们便可以在整个请求的处理流程中进行访问了
    //     final Long userId = JwtUtil
    //             .parseToken(token)
    //             .get("userId", Long.class);
    //     SystemUserInfoVo userInfo = service.getLoginUserById(userId);
    //     return Result.ok(userInfo);
    // }

    @Operation(summary = "获取登陆用户个人信息")
    @GetMapping("info")
    public Result<SystemUserInfoVo> info() {
        SystemUserInfoVo userInfo = loginService.getLoginUserByIdd(LoginUserHolder
                .getLoginUser()
                .getUserId());
        return Result.ok(userInfo);
    }

}
