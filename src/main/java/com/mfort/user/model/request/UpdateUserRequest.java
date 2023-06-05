package com.mfort.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateUserRequest {
    private Integer userNumber;
    private String name;
    private LocalDate birthAt;
    private String gender;
    private String password;
    private String email;

    public void setPassword(String password) {
        this.password = password;
    }
}