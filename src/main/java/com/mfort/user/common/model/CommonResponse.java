package com.mfort.user.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> {
    String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;
}
