package com.mfort.user.exception;


import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleValidationError(MethodArgumentNotValidException e) {
        return ResponseEntity.ok(CommonResponse.builder()
                .code(ResponseCode.INVALID_PARAMETER.getCode())
                .message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<CommonResponse> handleApiException(ApiException e) {
        return ResponseEntity.ok(CommonResponse.builder()
                .code(e.getResponseCode().getCode())
                .message(e.getResponseCode().getMessage())
                .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleUserNameNotFoundError(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleException(Exception e) {
        log.error("exception occurred", e);
        return ResponseEntity.ok(CommonResponse.builder()
                .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .build());
    }
}
