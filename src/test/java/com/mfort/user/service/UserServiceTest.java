package com.mfort.user.service;

import com.mfort.user.model.domain.User;
import com.mfort.user.utils.TestUtils;
import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.domain.SitterUser;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.RegisterParentRequest;
import com.mfort.user.model.request.RegisterSitterRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.model.vo.Child;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TestUtils testUtils;

    @Test
    public void save_sitter_correctly_save() {

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

        SitterUser sitterUser = userService.getSitter(userId);
        User user = sitterUser.getUser();

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(user.getGender()).isEqualTo("Male");
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(passwordEncoder.matches("password", user.getPassword())).isEqualTo(true);
        assertThat(user.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(sitterUser.getMinChildAge()).isEqualTo(3);
        assertThat(sitterUser.getMaxChildAge()).isEqualTo(5);
        assertThat(sitterUser.getBio()).isEqualTo("자기소개");
        assertThat(user.getCreatedAt()).isNotNull();
    }

    @Test
    public void save_parent_correctly_save() {

        String userId = testUtils.getRandomUserId();

        ParentSignUpRequest sut = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("password")
                .email("test001@gmail.com")
                .children(testUtils.getChildren())
                .requirements("요구사항")
                .build();

        userService.signUpParent(sut);

        ParentUser parentUser = userService.getParent(userId);
        User user = parentUser.getUser();

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(user.getGender()).isEqualTo("Male");
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(passwordEncoder.matches("password", user.getPassword())).isEqualTo(true);
        assertThat(user.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(parentUser.getRequirements()).isEqualTo("요구사항");

        List<Child> children = parentUser.getChildren();

        assertThat(children.size()).isEqualTo(2);
        assertThat(children.get(0).getBirthAt()).isEqualTo(LocalDate.of(2020, 1, 1));
        assertThat(children.get(1).getBirthAt()).isEqualTo(LocalDate.of(2021, 1, 1));
        assertThat(children.get(0).getGender()).isEqualTo("Male");
        assertThat(children.get(1).getGender()).isEqualTo("Female");
    }

    @Test
    public void get_user_detail_sitter() {

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

        UserInfoResponse user = userService.getUserDetail(userId);

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getMinChildAge()).isEqualTo(3);
        assertThat(user.getMaxChildAge()).isEqualTo(5);
        assertThat(user.getBio()).isEqualTo("자기소개");
    }

    @Test
    public void get_user_detail_parent() {

        String userId = testUtils.getRandomUserId();

        ParentSignUpRequest sut = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("password")
                .email("test001@gmail.com")
                .children(testUtils.getChildren())
                .requirements("요구사항")
                .build();

        userService.signUpParent(sut);

        UserInfoResponse user = userService.getUserDetail(userId);

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getRequirements()).isEqualTo("요구사항");
        assertThat(user.getChildren().get(0).getBirthAt()).isEqualTo(LocalDate.of(2020, 1, 1));
        assertThat(user.getChildren().get(1).getBirthAt()).isEqualTo(LocalDate.of(2021, 1, 1));
        assertThat(user.getChildren().get(0).getGender()).isEqualTo("Male");
        assertThat(user.getChildren().get(1).getGender()).isEqualTo("Female");
    }

    @Test
    public void register_sitter() {

        String userId = testUtils.getRandomUserId();

        ParentSignUpRequest sut = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("password")
                .email("test001@gmail.com")
                .children(testUtils.getChildren())
                .requirements("요구사항")
                .build();

        userService.signUpParent(sut);

        RegisterSitterRequest request = RegisterSitterRequest.builder()
                .minChildAge(1)
                .maxChildAge(2)
                .bio("자기소개")
                .build();

        userService.registerSitter(request, userId);
    }

    @Test
    public void register_parent() {

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

        RegisterParentRequest request = RegisterParentRequest.builder()
                .children(testUtils.getChildren())
                .requirements("요구사항")
                .build();

        userService.registerParent(request, userId);
    }
}
