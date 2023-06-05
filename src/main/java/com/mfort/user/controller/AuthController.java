package com.mfort.user.controller;

import com.mfort.user.jwt.JwtToken;
import com.mfort.user.model.request.LoginRequest;
import com.mfort.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtToken jwtToken = authService.getToken(loginRequest.getUserId(), loginRequest.getPassword());

        return ResponseEntity.ok(jwtToken);

    }

}
