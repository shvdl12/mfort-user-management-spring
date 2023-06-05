package com.mfort.user.repository;

import com.mfort.user.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserId(String userId);

    User findByUserId(String userId);

}
