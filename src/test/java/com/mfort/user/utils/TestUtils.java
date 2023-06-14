package com.mfort.user.utils;

import com.mfort.user.model.domain.User;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.RegisterSitterRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.model.vo.Child;
import com.mfort.user.service.AuthService;
import com.mfort.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class TestUtils {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    public String getRandomUserId() {
        return "user" + System.nanoTime();
    }

    public List<Child> getChildren() {
        return Arrays.asList(
                new Child(LocalDate.of(2020, 1, 1), "Male"),
                new Child(LocalDate.of(2021, 1, 1), "Female")
        );
    }

    public String getToken(String userId, String password) {
        return authService.getToken(userId, password).getAccessToken();
    }

    public void createSitter(String userId, String password) {
        SitterSignUpRequest user = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password(password)
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
                .build();

        userService.signUpSitter(user);
    }

    public void createParent(String userId, String password) {
        ParentSignUpRequest user = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password(password)
                .email("test001@gmail.com")
                .children(getChildren())
                .requirements("요구사항")
                .build();

        userService.signUpParent(user);
    }

    public void registerSitter(String userId) {
        userService.registerSitter(RegisterSitterRequest.builder()
                .minChildAge(0)
                .maxChildAge(5)
                .build(), userId);
    }

    public User getUser(String userId) {
        return User.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .build();
    }
}
