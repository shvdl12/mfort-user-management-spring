package com.mfort.user.repository;

import com.mfort.user.model.domain.ParentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<ParentUser, Integer> {
    boolean existsByUserId(String userId);
    ParentUser findByUserId(String userId);
}
