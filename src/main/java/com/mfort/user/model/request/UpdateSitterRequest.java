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
public class UpdateSitterRequest extends UpdateUserRequest {
    private Integer minChildAge;
    private Integer maxChildAge;
    private String bio;
}
