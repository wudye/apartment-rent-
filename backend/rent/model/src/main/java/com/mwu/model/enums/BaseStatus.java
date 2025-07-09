package com.mwu.model.enums;

public enum BaseStatus  implements BaseEnum{

    ENABLE(1, "正常"),

    DISABLE(0, "禁用");


    private Integer code;

    private String name;

    BaseStatus(Integer code, String name) {
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
