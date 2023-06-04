package com.mfort.user.repository;

import com.mfort.user.model.domain.Sitter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SitterRepositoryTest {

    @Autowired
    private SitterRepository sitterRepository;

    @Test
    public void save_sitter_correctly_save() {

        Sitter sut = Sitter.builder()
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

        Sitter sitter = sitterRepository.findByUserId("test001");

        assertThat(sitter.getName()).isEqualTo("홍길동");
        assertThat(sitter.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(sitter.getGender()).isEqualTo("Mail");
        assertThat(sitter.getUserId()).isEqualTo("test001");
        assertThat(sitter.getPassword()).isEqualTo("password");
        assertThat(sitter.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(sitter.getMinChildAge()).isEqualTo(3);
        assertThat(sitter.getMaxChildAge()).isEqualTo(5);
        assertThat(sitter.getBio()).isEqualTo("자기소개");
        assertThat(sitter.getCreatedAt()).isNotNull();
    }
}
