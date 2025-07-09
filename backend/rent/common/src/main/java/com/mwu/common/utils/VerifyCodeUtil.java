package com.mwu.common.utils;

import java.util.Random;

public class VerifyCodeUtil {
    public static String getVerifyCode(int length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code .append(random.nextInt(10)); // 0-9的数字
        }

        return code.toString();
    }

}
