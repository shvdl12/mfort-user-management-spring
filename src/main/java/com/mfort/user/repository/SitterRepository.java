package com.mfort.user.repository;

import com.mfort.user.model.domain.SitterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SitterRepository extends JpaRepository<SitterUser, Integer> {
    boolean existsByUserId(String userId);

    SitterUser findByUserId(String userId);

}
