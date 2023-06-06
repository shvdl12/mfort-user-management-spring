package com.mfort.user.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.mfort.user.model.vo.Child;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponse {
    private Integer userNumber;
    private String name;
    private LocalDate birthAt;
    private String gender;
    private String userId;
    private String email;

    private Integer minChildAge;
    private Integer maxChildAge;
    private String bio;

    private List<Child> children;
    private String requirements;
}
