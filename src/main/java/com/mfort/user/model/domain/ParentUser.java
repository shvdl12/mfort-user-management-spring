package com.mfort.user.model.domain;

import com.mfort.user.model.vo.Children;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PARENT")
@TypeDef(name = "json", typeClass = JsonType.class)
@Getter
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@EntityListeners(AuditingEntityListener.class)
@PrimaryKeyJoinColumn(name = "userNumber")
public class ParentUser extends User {
    @Type(type = "json")
    @Column(name = "children", columnDefinition = "longtext")
    private List<Children> children;
    private String requirements;
    @CreatedDate
    private LocalDateTime parentCreatedAt;
}
