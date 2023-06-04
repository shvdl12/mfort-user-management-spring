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
public class SignUpRequest {
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;
    @NotNull(message = "생일을 입력해주세요.")
    private LocalDate birthAt;
    private String gender;
    @NotEmpty(message = "아이디를 입력해주세요.")
    private String userId;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    @Email(message = "비밀번호를 입력해주세요.")
    private String email;
}
