package com.mwu.common.result;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T body,  ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);

        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;

    }

    public static <T> Result<T> ok(T body) {
        return build(body, ResultCodeEnum.SUCCESS);
    }
    public static <T> Result<T> ok() {
        return Result.ok(null );
    }

    public static <T> Result<T> fail() {
        return Result.build(null, ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }



}
