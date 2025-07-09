package com.mwu.common.exception;

import com.mwu.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class LeaseException extends  RuntimeException{
    private Integer code;
    public LeaseException( String message, Integer code) {
        super(message);
        this.code = code;
    }

    public LeaseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

}
