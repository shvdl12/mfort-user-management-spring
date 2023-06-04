package com.mfort.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SitterSignUpRequest extends SignUpRequest {
    @NotNull(message = "최소 아이 연령을 입력해주세요.")
    private Integer minChildAge;
    @NotNull(message = "최대 아이 연령을 입력해주세요.")
    private Integer maxChildAge;
    private String bio;
}
