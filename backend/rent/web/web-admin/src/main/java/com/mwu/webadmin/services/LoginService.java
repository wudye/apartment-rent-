package com.mwu.webadmin.services;

import com.mwu.webadmin.vo.login.CaptchaVo;
import com.mwu.webadmin.vo.login.LoginVo;
import com.mwu.webadmin.vo.system.SystemUserInfoVo;

public interface LoginService {


    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);


    SystemUserInfoVo getLoginUserByIdd(Long userId);
}
