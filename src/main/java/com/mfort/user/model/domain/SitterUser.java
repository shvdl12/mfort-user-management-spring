package com.mfort.user.model.domain;

import com.mfort.user.model.request.UpdateSitterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SITTER")
@Getter
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SitterUser {
    @Id
    private int userNumber;
    private Integer minChildAge;
    private Integer maxChildAge;
    private String bio;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USER_NUMBER")
    private User user;

    @CreatedDate
    private LocalDateTime sitterCreatedAt;

    public void updateSitter(UpdateSitterRequest request) {
        user.updateUser(request);
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
