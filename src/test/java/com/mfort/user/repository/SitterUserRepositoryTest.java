package com.mfort.user.repository;

import com.mfort.user.model.domain.User;
import com.mfort.user.utils.TestUtils;
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
    @Autowired
    private TestUtils testUtils;

    @Test
    public void save_sitter_correctly_save() {

        String userId = testUtils.getRandomUserId();

        SitterUser sut = SitterUser.builder()
                .user(testUtils.getUser(userId))
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
                .build();

        sitterRepository.save(sut);

        SitterUser sitterUser = sitterRepository.findByUserUserId(userId);
        User user = sitterUser.getUser();

        assertThat(user.getName()).isEqualTo("홍길동");
        assertThat(user.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(user.getGender()).isEqualTo("Male");
        assertThat(user.getUserId()).isEqualTo(userId);
        assertThat(user.getPassword()).isEqualTo("qwer1234!");
        assertThat(user.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(sitterUser.getMinChildAge()).isEqualTo(3);
        assertThat(sitterUser.getMaxChildAge()).isEqualTo(5);
        assertThat(sitterUser.getBio()).isEqualTo("자기소개");
        assertThat(user.getCreatedAt()).isNotNull();
    }
}
