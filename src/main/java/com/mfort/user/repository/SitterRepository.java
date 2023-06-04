package com.mfort.user.repository;

import com.mfort.user.model.domain.Sitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitterRepository extends JpaRepository<Sitter, Integer> {
    Sitter findByUserId(String userId);
}
