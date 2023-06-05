package com.mfort.user.repository;

import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.vo.Children;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Test
    public void save_parent_correctly_save() {

        String userId = "user" + System.currentTimeMillis();

        List<Children> sutChildren = Arrays.asList(
                new Children(LocalDate.of(2010, 1, 1), "Mail"),
                new Children(LocalDate.of(2011, 1, 1), "Femail")
        );

        ParentUser sut = ParentUser.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Mail")
                .userId(userId)
                .password("password")
                .email("test001@gmail.com")
                .children(sutChildren)
                .requirements("요구사항")
                .build();

        parentRepository.save(sut);

        ParentUser parentUser = parentRepository.findByUserId(userId);

        assertThat(parentUser.getName()).isEqualTo("홍길동");
        assertThat(parentUser.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(parentUser.getGender()).isEqualTo("Mail");
        assertThat(parentUser.getUserId()).isEqualTo(userId);
        assertThat(parentUser.getPassword()).isEqualTo("password");
        assertThat(parentUser.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(parentUser.getRequirements()).isEqualTo("요구사항");

        List<Children> children = parentUser.getChildren();

        assertThat(children.size()).isEqualTo(2);
        assertThat(children.get(0).getBirthAt()).isEqualTo(LocalDate.of(2010, 1, 1));
        assertThat(children.get(1).getBirthAt()).isEqualTo(LocalDate.of(2011, 1, 1));
        assertThat(children.get(0).getGender()).isEqualTo("Mail");
        assertThat(children.get(1).getGender()).isEqualTo("Femail");
    }
}
