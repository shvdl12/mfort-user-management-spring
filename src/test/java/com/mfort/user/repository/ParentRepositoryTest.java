package com.mfort.user.repository;

import com.mfort.user.model.domain.Parent;
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

        List<Parent.Children> sutChildren = Arrays.asList(
                new Parent.Children(LocalDate.of(2010, 1, 1), "Mail"),
                new Parent.Children(LocalDate.of(2011, 1, 1), "Femail")
        );

        Parent sut = Parent.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Mail")
                .userId("test001")
                .password("password")
                .email("test001@gmail.com")
                .children(sutChildren)
                .requirements("요구사항")
                .build();

        parentRepository.save(sut);

        Parent parent = parentRepository.findByUserId("test001");

        assertThat(parent.getName()).isEqualTo("홍길동");
        assertThat(parent.getBirthAt()).isEqualTo(LocalDate.of(1990, 1, 1));
        assertThat(parent.getGender()).isEqualTo("Mail");
        assertThat(parent.getUserId()).isEqualTo("test001");
        assertThat(parent.getPassword()).isEqualTo("password");
        assertThat(parent.getEmail()).isEqualTo("test001@gmail.com");
        assertThat(parent.getRequirements()).isEqualTo("요구사항");

        List<Parent.Children> children = parent.getChildren();

        assertThat(children.size()).isEqualTo(2);
        assertThat(children.get(0).getBirthAt()).isEqualTo(LocalDate.of(2010, 1, 1));
        assertThat(children.get(1).getBirthAt()).isEqualTo(LocalDate.of(2011, 1, 1));
        assertThat(children.get(0).getGender()).isEqualTo("Mail");
        assertThat(children.get(1).getGender()).isEqualTo("Femail");
    }
}
