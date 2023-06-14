package com.mfort.user.query;

import com.mfort.user.model.domain.SitterUser;
import com.mfort.user.model.request.UpdateSitterRequest;
import com.mfort.user.repository.SitterRepository;
import com.mfort.user.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class SitterRepositoryTest {

    @Autowired
    private SitterRepository sitterRepository;
    @Autowired
    private TestUtils testUtils;

    @Test
    public void sitter_insert() {

        String userId = testUtils.getRandomUserId();

        SitterUser sut = SitterUser.builder()
                .user(testUtils.getUser(userId))
                .minChildAge(0)
                .maxChildAge(3)
                .bio("안녕하세요")
                .build();

        sitterRepository.save(sut);
    }

    @Test
    public void sitter_select() {

        String userId = testUtils.getRandomUserId();

        SitterUser sut = SitterUser.builder()
                .user(testUtils.getUser(userId))
                .minChildAge(0)
                .maxChildAge(3)
                .bio("안녕하세요")
                .build();

        sitterRepository.save(sut);

        List<SitterUser> users = sitterRepository.findAll();

        System.out.println(users.toString());
    }

    @Test
    public void sitter_update() {

        String userId = testUtils.getRandomUserId();

        SitterUser sut = SitterUser.builder()
                .user(testUtils.getUser(userId))
                .minChildAge(0)
                .maxChildAge(3)
                .bio("안녕하세요")
                .build();


        sitterRepository.save(sut);

        sut.updateSitter(UpdateSitterRequest.builder().name("홍길순").minChildAge(1).build());

        sitterRepository.save(sut);
        sitterRepository.flush();
    }

    @Test
    public void sitter_delete() {

        String userId = testUtils.getRandomUserId();

        SitterUser sut = SitterUser.builder()
                .user(testUtils.getUser(userId))
                .minChildAge(0)
                .maxChildAge(3)
                .bio("안녕하세요")
                .build();

        sitterRepository.save(sut);
        sitterRepository.delete(sut);
        sitterRepository.flush();
    }
}
