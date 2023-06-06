package com.mfort.user.model.request;

import com.mfort.user.common.annotation.Gender;
import com.mfort.user.common.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateUserRequest {
    private Integer userNumber;
    private String name;
    private LocalDate birthAt;
    @Gender
    private String gender;
    @Password
    private String password;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    public void setPassword(String password) {
        this.password = password;
    }
}
