package com.mfort.user.model.request;

import com.mfort.user.model.vo.Children;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ParentSignUpRequest extends SignUpRequest {
    @NotNull(message = "아이 정보를 입력해주세요.")
    private List<Children> children;
    private String requirements;
}
