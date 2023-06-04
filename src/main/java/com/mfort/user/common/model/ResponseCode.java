package com.mfort.user.common.model;

public enum ResponseCode {

    SUCCESS("0000", "success"),
    INVALID_PARAMETER("1000", "invalid parameter"),
    DUPLICATE_USER_ID("1001", "duplicate user id"),
    INTERNAL_SERVER_ERROR("9999", "internal server error")
    ;


    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }
}
