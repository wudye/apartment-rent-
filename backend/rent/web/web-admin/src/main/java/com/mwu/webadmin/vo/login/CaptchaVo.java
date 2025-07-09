package com.mwu.webadmin.vo.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description =  "图像验证码")
public class CaptchaVo {

    @Schema(description = "验证码图片信息")
    private String image;

    @Schema(description = "验证码key")
    private String key;
}

