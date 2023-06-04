package com.mfort.user.controller;

import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup/sitter")
    public ResponseEntity<CommonResponse> signUpSitter(@Valid @RequestBody SitterSignUpRequest request) {

        userService.signUpSitter(request);

        CommonResponse response = CommonResponse.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup/parent")
    public ResponseEntity<CommonResponse> signUpParent(@Valid @RequestBody ParentSignUpRequest request) {

        userService.signUpParent(request);

        CommonResponse response = CommonResponse.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .build();

        return ResponseEntity.ok(response);
    }
}
