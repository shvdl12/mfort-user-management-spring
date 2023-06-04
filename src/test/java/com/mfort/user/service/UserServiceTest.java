package com.mfort.user.service;

import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.domain.SitterUser;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.model.vo.Children;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void save_sitter_correctly_save() {

        SitterSignUpRequest sut = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Mail")
                .userId("test001")
                .password("password")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
                .build();

        userService.signUpSitter(sut);

        SitterUser sitterUser = userService.getSitter("test001");

        assertThat(sitterUser.getName()).isEqualTo("홍길동");
        assertThat(sitterUser.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(sitterUser.getGender()).isEqualTo("Mail");
        assertThat(sitterUser.getUserId()).isEqualTo("test001");
        assertThat(passwordEncoder.matches("password", sitterUser.getPassword())).isEqualTo(true);
        assertThat(sitterUser.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(sitterUser.getMinChildAge()).isEqualTo(3);
        assertThat(sitterUser.getMaxChildAge()).isEqualTo(5);
        assertThat(sitterUser.getBio()).isEqualTo("자기소개");
        assertThat(sitterUser.getCreatedAt()).isNotNull();
    }

    @Test
    public void save_parent_correctly_save() {

        List<Children> sutChildren = Arrays.asList(
                new Children(LocalDate.of(2010, 1, 1), "Mail"),
                new Children(LocalDate.of(2011, 1, 1), "Femail")
        );

        ParentSignUpRequest sut = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Mail")
                .userId("test001")
                .password("password")
                .email("test001@gmail.com")
                .children(sutChildren)
                .requirements("요구사항")
                .build();

        userService.signUpParent(sut);

        ParentUser parentUser = userService.getParent("test001");

        assertThat(parentUser.getName()).isEqualTo("홍길동");
        assertThat(parentUser.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(parentUser.getGender()).isEqualTo("Mail");
        assertThat(parentUser.getUserId()).isEqualTo("test001");
        assertThat(passwordEncoder.matches("password", parentUser.getPassword())).isEqualTo(true);
        assertThat(parentUser.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(parentUser.getRequirements()).isEqualTo("요구사항");

        List<Children> children = parentUser.getChildren();

        assertThat(children.size()).isEqualTo(2);
        assertThat(children.get(0).getBirthAt()).isEqualTo(LocalDate.of(2010, 1, 1));
        assertThat(children.get(1).getBirthAt()).isEqualTo(LocalDate.of(2011, 1, 1));
        assertThat(children.get(0).getGender()).isEqualTo("Mail");
        assertThat(children.get(1).getGender()).isEqualTo("Femail");
    }

    @Test
    public void get_user_detail_sitter() {
        SitterSignUpRequest sut = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Mail")
                .userId("test001")
                .password("password")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
                .build();

        userService.signUpSitter(sut);

        UserInfoResponse user = userService.getUserDetail("test001");

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getMinChildAge()).isEqualTo(3);
        assertThat(user.getMaxChildAge()).isEqualTo(5);
        assertThat(user.getBio()).isEqualTo("자기소개");
    }

    @Test
    public void get_user_detail_parent() {
        List<Children> sutChildren = Arrays.asList(
                new Children(LocalDate.of(2010, 1, 1), "Mail"),
                new Children(LocalDate.of(2011, 1, 1), "Femail")
        );

        ParentSignUpRequest sut = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Mail")
                .userId("test001")
                .password("password")
                .email("test001@gmail.com")
                .children(sutChildren)
                .requirements("요구사항")
                .build();

        userService.signUpParent(sut);

        UserInfoResponse user = userService.getUserDetail("test001");

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getRequirements()).isEqualTo("요구사항");
        assertThat(user.getChildren().get(0).getBirthAt()).isEqualTo(LocalDate.of(2010, 1, 1));
        assertThat(user.getChildren().get(1).getBirthAt()).isEqualTo(LocalDate.of(2011, 1, 1));
        assertThat(user.getChildren().get(0).getGender()).isEqualTo("Mail");
        assertThat(user.getChildren().get(1).getGender()).isEqualTo("Femail");
    }
}
