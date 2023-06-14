package com.mfort.user.query;

import com.mfort.user.model.domain.User;
import com.mfort.user.model.request.UpdateUserRequest;
import com.mfort.user.repository.UserRepository;
import com.mfort.user.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestUtils testUtils;

    @Test
    public void user_insert() {

        String userId = testUtils.getRandomUserId();

        User sut = testUtils.getUser(userId);

        userRepository.save(sut);
    }

    @Test
    public void user_select() {

        String userId = testUtils.getRandomUserId();

        User sut = testUtils.getUser(userId);

        userRepository.save(sut);

        List<User> users = userRepository.findAll();

        System.out.println(users.toString());
    }

    @Test
    public void user_update() {

        String userId = testUtils.getRandomUserId();

        User sut = testUtils.getUser(userId);

        userRepository.saveAndFlush(sut);

        sut.updateUser(UpdateUserRequest.builder().name("홍길순").build());

        userRepository.saveAndFlush(sut);
    }

    @Test
    public void user_delete() {

        String userId = testUtils.getRandomUserId();

        User sut = testUtils.getUser(userId);

        userRepository.saveAndFlush(sut);
        userRepository.delete(sut);
        userRepository.flush();
    }
}
