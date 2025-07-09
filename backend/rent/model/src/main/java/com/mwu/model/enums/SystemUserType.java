package com.mwu.model.enums;

public enum SystemUserType implements BaseEnum {
    ADMIN(0, "管理员"),
    COMMON(1, "普通用户");

    private Integer code;
    private String name;

    SystemUserType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    @Override
    public Integer getCode() {
        return code;
    }
    @Override
    public String getName() {
        return name;
    }


}
