package com.mwu.webadmin.services.impl;

import com.mwu.common.exception.LeaseException;
import com.mwu.common.redis.RedisConstant;
import com.mwu.common.result.ResultCodeEnum;
import com.mwu.common.utils.JwtUtil;
import com.mwu.model.entity.SystemUser;
import com.mwu.model.enums.BaseStatus;
import com.mwu.webadmin.mapper.SystemUserMapper;
import com.mwu.webadmin.repository.SystemLoginRepository;
import com.mwu.webadmin.services.LoginService;
import com.mwu.webadmin.vo.login.CaptchaVo;
import com.mwu.webadmin.vo.login.LoginVo;
import com.mwu.webadmin.vo.system.SystemUserInfoVo;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SystemLoginRepository systemLoginRepository;


    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 4);
        captcha.setCharType(Captcha.TYPE_DEFAULT);

        final String code = captcha.text().toLowerCase();
        final String image = captcha.toBase64();

        final String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        redisTemplate.opsForValue()
                .set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

        System.out.println("captchaKey: " + key);
        System.out.println("captchaCode: " + code);
        return new CaptchaVo(code, image);
    }

    @Override
    public String login(LoginVo loginVo) {
        if (!StringUtils.hasText(loginVo.getCaptchaCode())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        System.out.println("Captcha Code1: " + loginVo.getCaptchaCode());
        System.out.println("Captcha Key1: " + loginVo.getCaptchaKey());

        String code = redisTemplate.opsForValue()
                .get(loginVo.getCaptchaKey());
        System.out.println("Captcha Code from Redis: " + code);
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);

//            return null;
        }
        System.out.println("Captcha Code2: " + code);
        if(!code.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }
        System.out.println("Captcha Code3: " + loginVo.getCaptchaCode());

        Optional<SystemUser> systemUser = systemLoginRepository.findByUsername(loginVo.getUsername());
        if (systemUser.isEmpty()) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        System.out.println("User found: " + systemUser.get().getUsername());
        SystemUser user = systemUser.get();
        if (user.getStatus() == BaseStatus.DISABLE) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);

        }
        System.out.println("Login attempt for user: " + user.getUsername());
        System.out.println("Provided password: " + loginVo.getPassword());
        System.out.println("Stored password hash: " + user.getPassword());

        if (!user.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }
        return JwtUtil.createToken(user.getId(), user.getUsername());
    }

    @Override
    public SystemUserInfoVo getLoginUserByIdd(Long userId) {
        Optional<SystemUser> systemUser = systemLoginRepository.findById(userId);
        SystemUser user = systemUser.get();
        SystemUserInfoVo userInfoVo = new SystemUserInfoVo();
            userInfoVo.setName(user.getUsername());
            userInfoVo.setAvatarUrl(user.getAvatarUrl());

            return userInfoVo;

    }
}
