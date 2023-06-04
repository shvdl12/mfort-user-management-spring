package com.mfort.user.exception;


import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(DuplicateUserIdException.class)
    public ResponseEntity<CommonResponse> handleDuplicateUserIdError(DuplicateUserIdException e) {
        return ResponseEntity.ok(CommonResponse.builder()
                .code(ResponseCode.DUPLICATE_USER_ID.getCode())
                .message(e.getMessage())
                .build());
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
