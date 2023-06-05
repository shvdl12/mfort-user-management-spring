package com.mfort.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterSitterRequest {
    private Integer userNumber;
    private Integer minChildAge;
    private Integer maxChildAge;
    private String bio;
}
