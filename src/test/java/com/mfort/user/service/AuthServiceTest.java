package com.mfort.user.service;

import com.mfort.user.jwt.model.JwtToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    public void get_token() {
        JwtToken token = authService.getToken("test001", "password");

        assertThat(token).isNotNull();
        assertThat(token.getAccessToken()).isNotNull();
        assertThat(token.getRefreshToken()).isNotNull();
        assertThat(token.getGrantType()).isEqualTo("Bearer");
    }

}
