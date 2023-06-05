package com.mfort.user.exception;

import com.mfort.user.common.model.ResponseCode;

public class ApiException extends RuntimeException {

    private ResponseCode responseCode;

    public ApiException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
