package com.mfort.user.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.model.request.UpdateParentRequest;
import com.mfort.user.model.request.UpdateSitterRequest;
import com.mfort.user.model.response.UserInfoResponse;
import com.mfort.user.model.vo.Child;
import com.mfort.user.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UpdateUserApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TestUtils testUtils;
    private final String path = "/user/update";

    @Test
    public void update_sitter_return_success() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createSitter(userId, password);
        String token = testUtils.getToken(userId, password);

        UpdateSitterRequest request = UpdateSitterRequest.builder()
                .minChildAge(6)
                .maxChildAge(8)
                .bio("안녕하세요 홍길동입니다.")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path + "/sitter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    TypeReference<CommonResponse<UserInfoResponse>> typeRef
                            = new TypeReference<CommonResponse<UserInfoResponse>>() {
                    };

                    CommonResponse<UserInfoResponse> res = objectMapper.readValue(response.getContentAsString(), typeRef);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                    assertThat(res.getData().getMinChildAge()).isEqualTo(6);
                    assertThat(res.getData().getMaxChildAge()).isEqualTo(8);
                    assertThat(res.getData().getBio()).isEqualTo("안녕하세요 홍길동입니다.");
                });
    }

    @Test
    public void update_parent_return_success() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createParent(userId, password);
        String token = testUtils.getToken(userId, password);

        List<Child> children = Arrays.asList(
                new Child(LocalDate.of(2020, 1, 1), "Female"),
                new Child(LocalDate.of(2021, 1, 1), "Male"),
                new Child(LocalDate.of(2023, 1, 1), "Male")
        );

        UpdateParentRequest request = UpdateParentRequest.builder()
                .children(children)
                .requirements("요리가 가능한 분")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path + "/parent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    TypeReference<CommonResponse<UserInfoResponse>> typeRef
                            = new TypeReference<CommonResponse<UserInfoResponse>>() {
                    };

                    CommonResponse<UserInfoResponse> res = objectMapper.readValue(response.getContentAsString(), typeRef);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                    assertThat(res.getData().getChildren()).hasSize(3);
                    assertThat(res.getData().getRequirements()).isEqualTo("요리가 가능한 분");
                });
    }
}
