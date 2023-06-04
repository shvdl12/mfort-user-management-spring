package com.mfort.user.repository;

import com.mfort.user.model.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    boolean existsByUserId(String userId);
    Parent findByUserId(String userId);
}
