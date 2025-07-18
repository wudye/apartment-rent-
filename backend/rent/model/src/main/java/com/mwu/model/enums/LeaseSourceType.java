package com.mwu.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LeaseSourceType implements  BaseEnum {
    NEW(1, "新签"),
    RENEW(2, "续约");

    @JsonValue
    private Integer code;

    private String name;

    LeaseSourceType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
