package com.mfort.user.repository;

import com.mfort.user.model.domain.SitterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SitterRepository extends JpaRepository<SitterUser, Integer> {
    boolean existsByUserId(String userId);
    SitterUser findByUserId(String userId);
    @Modifying
    @Query(value = "INSERT INTO TB_SITTER VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void insertOnlySitter( Integer userNumber,  Integer minChildAge
            ,  Integer maxChildAge,  String bio
            ,  LocalDateTime sitterCreatedAt);
}
