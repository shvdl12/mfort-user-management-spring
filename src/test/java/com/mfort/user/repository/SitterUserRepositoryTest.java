package com.mfort.user.repository;

import com.mfort.user.model.domain.SitterUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SitterUserRepositoryTest {

    @Autowired
    private SitterRepository sitterRepository;

    @Test
    public void save_sitter_correctly_save() {

        SitterUser sut = SitterUser.builder()
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

        sitterRepository.save(sut);

        SitterUser sitterUser = sitterRepository.findByUserId("test001");

        assertThat(sitterUser.getName()).isEqualTo("홍길동");
        assertThat(sitterUser.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(sitterUser.getGender()).isEqualTo("Mail");
        assertThat(sitterUser.getUserId()).isEqualTo("test001");
        assertThat(sitterUser.getPassword()).isEqualTo("password");
        assertThat(sitterUser.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(sitterUser.getMinChildAge()).isEqualTo(3);
        assertThat(sitterUser.getMaxChildAge()).isEqualTo(5);
        assertThat(sitterUser.getBio()).isEqualTo("자기소개");
        assertThat(sitterUser.getCreatedAt()).isNotNull();
    }
}