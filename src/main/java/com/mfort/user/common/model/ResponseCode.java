package com.mfort.user.common.model;

public enum ResponseCode {

    SUCCESS("0000", "success"),
    INVALID_ACCESS_TOKEN("1000", "invalid parameter"),
    INVALID_PARAMETER("1001", "invalid parameter"),
    DUPLICATE_USER_ID("1002", "duplicate user id"),
    ALREADY_REGISTER_SITTER("1003", "invalid parameter"),
    ALREADY_REGISTER_PARENT("1004", "invalid parameter"),
    SITTER_INFO_NOT_FOUND("1005", "invalid parameter"),
    PARENT_INFO_NOT_FOUND("1006", "invalid parameter"),
    INTERNAL_SERVER_ERROR("9999", "internal server error");


    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
