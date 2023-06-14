package com.mfort.user.query;

import com.mfort.user.model.domain.ParentUser;
import com.mfort.user.model.request.UpdateParentRequest;
import com.mfort.user.repository.ParentRepository;
import com.mfort.user.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private TestUtils testUtils;

    @Test
    public void parent_insert() {

        String userId = testUtils.getRandomUserId();

        ParentUser sut = ParentUser.builder()
                .user(testUtils.getUser(userId))
                .children(testUtils.getChildren())
                .requirements("요구")
                .build();

        parentRepository.save(sut);
    }

    @Test
    public void parent_select() {

        String userId = testUtils.getRandomUserId();

        ParentUser sut = ParentUser.builder()
                .user(testUtils.getUser(userId))
                .children(testUtils.getChildren())
                .requirements("요구")
                .build();


        parentRepository.save(sut);

        System.out.println("====================================================================================================");

        List<ParentUser> users = parentRepository.findAll();

        System.out.println(users.toString());
    }

    @Test
    public void parent_update() {

        String userId = testUtils.getRandomUserId();

        System.out.println("userId: " + userId);

        ParentUser sut = ParentUser.builder()
                .user(testUtils.getUser(userId))
                .children(testUtils.getChildren())
                .requirements("요구")
                .build();

        parentRepository.save(sut);
        System.out.println("====================================================================================================");

        sut.updateParent(UpdateParentRequest.builder().name("홍길순").build());

        parentRepository.save(sut);
        parentRepository.flush();
    }

    @Test
    public void parent_delete() {

        String userId = testUtils.getRandomUserId();

        ParentUser sut = ParentUser.builder()
                .user(testUtils.getUser(userId))
                .children(testUtils.getChildren())
                .requirements("요구")
                .build();

        parentRepository.saveAndFlush(sut);
        System.out.println("====================================================================================================");

        parentRepository.delete(sut);
        parentRepository.flush();
    }
}
