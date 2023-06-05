package com.mfort.user.model.domain;

import com.mfort.user.model.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "TB_USER")
@Getter
@SuperBuilder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNumber;
    private String name;
    private LocalDate birthAt;
    private String gender;
    private String userId;
    private String password;
    private String email;
    @CreatedDate
    private LocalDateTime createdAt;

    public void updateUser(UpdateUserRequest request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }
        if (request.getBirthAt() != null) {
            this.birthAt = request.getBirthAt();
        }
        if (request.getGender() != null) {
            this.gender = request.getGender();
        }
        if (request.getEmail() != null) {
            this.email = request.getEmail();
        }
        if (request.getPassword() != null) {
            this.password = request.getPassword();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
