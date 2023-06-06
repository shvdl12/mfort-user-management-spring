package com.mfort.user.model.request;

import com.mfort.user.model.vo.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterParentRequest {
    @NotEmpty(message = "아이 정보를 입력해주세요.")
    private List<Child> children;
    private String requirements;
}
