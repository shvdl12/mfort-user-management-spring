package com.mfort.user.model.request;

import com.mfort.user.common.annotation.Gender;
import com.mfort.user.common.annotation.Password;
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
    @NotNull(message = "성별을 입력해주세요.")
    @Gender
    private String gender;
    @NotEmpty(message = "아이디를 입력해주세요.")
    private String userId;
    @NotNull(message = "비밀번호를 입력해주세요.")
    @Password
    private String password;
    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
