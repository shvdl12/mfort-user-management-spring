package com.mfort.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterSitterRequest {
    @NotNull(message = "최소 아이 연령을 입력해주세요.")
    private Integer minChildAge;
    @NotNull(message = "최대 아이 연령을 입력해주세요.")
    private Integer maxChildAge;
    private String bio;
}
