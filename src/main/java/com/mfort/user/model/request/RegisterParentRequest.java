package com.mfort.user.model.request;

import com.mfort.user.model.vo.Children;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterParentRequest {
    private List<Children> children;
    private String requirements;
}
