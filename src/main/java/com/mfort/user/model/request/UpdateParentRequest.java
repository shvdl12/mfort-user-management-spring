package com.mfort.user.model.request;

import com.mfort.user.model.vo.Child;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateParentRequest extends UpdateUserRequest {
    private List<Child> children;
    private String requirements;
}
