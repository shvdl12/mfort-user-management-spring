package com.mfort.user.service;

import com.mfort.user.service.AuthService;
import com.mfort.user.service.UserService;
import com.mfort.user.utils.TestUtils;
import com.mfort.user.jwt.JwtToken;
import com.mfort.user.model.request.SitterSignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    TestUtils testUtils;


    @Test
    public void get_token() {

        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest sut = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("password")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
                .build();

        userService.signUpSitter(sut);

        JwtToken token = authService.getToken(userId, "password");

        assertThat(token).isNotNull();
        assertThat(token.getAccessToken()).isNotNull();
        assertThat(token.getRefreshToken()).isNotNull();
        assertThat(token.getGrantType()).isEqualTo("Bearer");
    }

}
