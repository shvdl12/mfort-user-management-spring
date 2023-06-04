package com.mfort.user.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mfort.user.model.vo.Children;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PARENT")
@TypeDef(name = "json", typeClass = JsonType.class)
@Getter
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNumber;
    private String name;
    private LocalDate birthAt;
    private String gender;
    private String userId;
    private String password;
    private String email;
    @Type(type = "json")
    @Column(name = "children", columnDefinition = "longtext")
    private List<Children> children;
    private String requirements;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Parent(Integer userNumber, String name, LocalDate birthAt, String gender, String userId, String password
            , String email, List<Children> children, String requirements, LocalDateTime createdAt) {
        this.userNumber = userNumber;
        this.name = name;
        this.birthAt = birthAt;
        this.gender = gender;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.children = children;
        this.requirements = requirements;
        this.createdAt = createdAt;
    }
}
