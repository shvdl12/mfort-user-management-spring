package com.mfort.user.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SITTER")
@Getter
@SuperBuilder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@PrimaryKeyJoinColumn(name = "userNumber")
public class SitterUser extends User {
    private Integer minChildAge;
    private Integer maxChildAge;
    private String bio;

    @CreatedDate
    private LocalDateTime sitterCreatedAt;
}
