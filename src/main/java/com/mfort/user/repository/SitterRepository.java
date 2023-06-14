package com.mfort.user.repository;

import com.mfort.user.model.domain.SitterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SitterRepository extends JpaRepository<SitterUser, Integer> {
    boolean existsByUserUserId(String userId);

    SitterUser findByUserUserId(String userId);

    @Modifying
    @Query(value = "INSERT INTO TB_SITTER(USER_NUMBER, MIN_CHILD_AGE, MAX_CHILD_AGE, BIO, SITTER_CREATED_AT)" +
            " VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertOnlySitter(Integer userNumber, Integer minChildAge
            , Integer maxChildAge, String bio
            , LocalDateTime sitterCreatedAt);
}
