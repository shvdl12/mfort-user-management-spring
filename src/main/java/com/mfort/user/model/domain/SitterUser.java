package com.mfort.user.model.domain;

import com.mfort.user.model.request.UpdateSitterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
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
@DynamicUpdate
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

    public void updateSitter(UpdateSitterRequest request) {
        super.updateUser(request);
        if (request.getMinChildAge() != null) {
            this.minChildAge = request.getMinChildAge();
        }
        if (request.getMaxChildAge() != null) {
            this.maxChildAge = request.getMaxChildAge();
        }
        if (request.getBio() != null) {
            this.bio = request.getBio();
        }
    }
}
