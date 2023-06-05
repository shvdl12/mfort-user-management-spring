package com.mfort.user.controller;

import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.model.request.RegisterParentRequest;
import com.mfort.user.model.request.RegisterSitterRequest;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/detail")
    public ResponseEntity<?> getUserDetail(@AuthenticationPrincipal UserDetails userDetails) {

        CommonResponse<UserInfoResponse> response = CommonResponse.<UserInfoResponse>builder()
                .code(ResponseCode.SUCCESS.getCode())
                .data(userService.getUserDetail(userDetails.getUsername()))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/sitter")
    public ResponseEntity<CommonResponse> registerSitter(@Valid @RequestBody RegisterSitterRequest request
            , @AuthenticationPrincipal UserDetails userDetails) {

        userService.registerSitter(request, userDetails.getUsername());

        CommonResponse response = CommonResponse.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/parent")
    public ResponseEntity<CommonResponse> registerParent(@Valid @RequestBody RegisterParentRequest request
            , @AuthenticationPrincipal UserDetails userDetails) {

        userService.registerParent(request, userDetails.getUsername());

        CommonResponse response = CommonResponse.builder()
                .code(ResponseCode.SUCCESS.getCode())
                .build();

        return ResponseEntity.ok(response);
    }
}
