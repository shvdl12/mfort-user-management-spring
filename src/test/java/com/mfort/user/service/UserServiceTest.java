package com.mfort.user.service;

import com.mfort.user.model.domain.Parent;
import com.mfort.user.model.domain.Sitter;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
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

        Sitter sitter = userService.getSitter("test001");

        assertThat(sitter.getName()).isEqualTo("홍길동");
        assertThat(sitter.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(sitter.getGender()).isEqualTo("Mail");
        assertThat(sitter.getUserId()).isEqualTo("test001");
        assertThat(passwordEncoder.matches("password", sitter.getPassword())).isEqualTo(true);
        assertThat(sitter.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(sitter.getMinChildAge()).isEqualTo(3);
        assertThat(sitter.getMaxChildAge()).isEqualTo(5);
        assertThat(sitter.getBio()).isEqualTo("자기소개");
        assertThat(sitter.getCreatedAt()).isNotNull();
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

        Parent parent = userService.getParent("test001");

        assertThat(parent.getName()).isEqualTo("홍길동");
        assertThat(parent.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(parent.getGender()).isEqualTo("Mail");
        assertThat(parent.getUserId()).isEqualTo("test001");
        assertThat(passwordEncoder.matches("password", parent.getPassword())).isEqualTo(true);
        assertThat(parent.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(parent.getRequirements()).isEqualTo("요구사항");

        List<Children> children = parent.getChildren();

        assertThat(children.size()).isEqualTo(2);
        assertThat(children.get(0).getBirthAt()).isEqualTo(LocalDate.of(2010, 1, 1));
        assertThat(children.get(1).getBirthAt()).isEqualTo(LocalDate.of(2011, 1, 1));
        assertThat(children.get(0).getGender()).isEqualTo("Mail");
        assertThat(children.get(1).getGender()).isEqualTo("Femail");
    }
}
