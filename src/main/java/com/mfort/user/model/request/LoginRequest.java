package com.mfort.user.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
