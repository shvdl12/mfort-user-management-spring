package com.mfort.user.model.domain;

import com.mfort.user.model.request.UpdateParentRequest;
import com.mfort.user.model.vo.Child;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class ParentUser {
    @Id
    private int userNumber;
    @Type(type = "json")
    @Column(name = "children", columnDefinition = "longtext")
    private List<Child> children;
    private String requirements;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USER_NUMBER")
    private User user;

    @CreatedDate
    private LocalDateTime parentCreatedAt;

    public void updateParent(UpdateParentRequest request) {
        user.updateUser(request);
        if (request.getChildren() != null) {
            this.children = request.getChildren();
        }
        if (request.getRequirements() != null) {
            this.requirements = request.getRequirements();
        }
    }
}
