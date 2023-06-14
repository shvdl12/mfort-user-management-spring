package com.mfort.user.repository;

import com.mfort.user.model.domain.ParentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ParentRepository extends JpaRepository<ParentUser, Integer> {
    boolean existsByUserUserId(String userId);

    ParentUser findByUserUserId(String userId);

    @Modifying
    @Query(value = "INSERT INTO TB_PARENT(USER_NUMBER, CHILDREN, REQUIREMENTS, PARENT_CREATED_AT) VALUES (?1, ?2, ?3, ?4)",
            nativeQuery = true)
    void insertOnlyParent(Integer userNumber, String children, String requirements, LocalDateTime parentCreatedAt);
}
