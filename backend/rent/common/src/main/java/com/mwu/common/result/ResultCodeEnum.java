package com.mwu.common.result;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "Success"),
    FAIL(201, "Failure"),
    PARAM_ERROR(202, "Invalid parameter"),
    SERVICE_ERROR(203, "Service exception"),
    DATA_ERROR(204, "Data exception"),
    ILLEGAL_REQUEST(205, "Illegal request"),
    REPEAT_SUBMIT(206, "Duplicate submission"),
    DELETE_ERROR(207, "Please delete child items first"),

    ADMIN_ACCOUNT_EXIST_ERROR(301, "Account already exists"),
    ADMIN_CAPTCHA_CODE_ERROR(302, "Incorrect verification code"),
    ADMIN_CAPTCHA_CODE_EXPIRED(303, "Verification code expired"),
    ADMIN_CAPTCHA_CODE_NOT_FOUND(304, "Verification code not entered"),

    ADMIN_LOGIN_AUTH(305, "Not logged in"),
    ADMIN_ACCOUNT_NOT_EXIST_ERROR(306, "Account does not exist"),
    ADMIN_ACCOUNT_ERROR(307, "Incorrect username or password"),
    ADMIN_ACCOUNT_DISABLED_ERROR(308, "This user has been disabled"),
    ADMIN_ACCESS_FORBIDDEN(309, "No access permission"),

    ADMIN_APARTMENT_DELETE_ERROR(310, "Delete room information before deleting apartment information"),

    APP_LOGIN_AUTH(501, "Not logged in"),
    APP_LOGIN_PHONE_EMPTY(502, "Phone number is empty"),
    APP_LOGIN_CODE_EMPTY(503, "Verification code is empty"),
    APP_SEND_SMS_TOO_OFTEN(504, "Verification code sent too frequently"),
    APP_LOGIN_CODE_EXPIRED(505, "Verification code expired"),
    APP_LOGIN_CODE_ERROR(506, "Incorrect verification code"),
    APP_ACCOUNT_DISABLED_ERROR(507, "This user has been disabled"),

    TOKEN_EXPIRED(601, "Token expired"),
    TOKEN_INVALID(602, "Invalid token");

    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}