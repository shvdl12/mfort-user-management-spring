package com.mfort.user.model.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SITTER")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Sitter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNumber;
    private String name;
    private LocalDate birthAt;
    private String gender;
    private String userId;
    private String password;
    private String email;
    private Integer minChildAge;
    private Integer maxChildAge;
    private String bio;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Sitter(Integer userNumber, String name, LocalDate birthAt, String gender, String userId, String password
            , String email, Integer minChildAge, Integer maxChildAge, String bio, LocalDateTime createdAt) {
        this.userNumber = userNumber;
        this.name = name;
        this.birthAt = birthAt;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.minChildAge = minChildAge;
        this.maxChildAge = maxChildAge;
        this.bio = bio;
        this.createdAt = createdAt;
    }
}
