package com.mfort.user.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.model.response.UserInfoResponse;
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

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
//@Transactional
public class FetchUserDetailApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TestUtils testUtils;
    private final String path = "/user/detail";

    @Test
    public void fetch_sitter_info_detail() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createSitter(userId, password);
        String token = testUtils.getToken(userId, password);

        mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    TypeReference<CommonResponse<UserInfoResponse>> typeRef
                            = new TypeReference<CommonResponse<UserInfoResponse>>() {
                    };

                    CommonResponse<UserInfoResponse> res = objectMapper.readValue(response.getContentAsString(), typeRef);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                    assertThat(res.getData().getUserId()).isEqualTo(userId);
                    assertThat(res.getData().getMinChildAge()).isEqualTo(3);
                    assertThat(res.getData().getMaxChildAge()).isEqualTo(5);
                    assertThat(res.getData().getBio()).isEqualTo("자기소개");
                });
    }

    @Test
    public void fetch_parent_info_detail() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createParent(userId, password);
        String token = testUtils.getToken(userId, password);

        mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    TypeReference<CommonResponse<UserInfoResponse>> typeRef
                            = new TypeReference<CommonResponse<UserInfoResponse>>() {
                    };

                    CommonResponse<UserInfoResponse> res = objectMapper.readValue(response.getContentAsString(), typeRef);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                    assertThat(res.getData().getUserId()).isEqualTo(userId);
                    assertThat(res.getData().getChildren()).hasSize(2);
                    assertThat(res.getData().getRequirements()).isEqualTo("요구사항");
                });
    }

    @Test
    public void fetch_all_info_detail() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createParent(userId, password);
        testUtils.registerSitter(userId);

        String token = testUtils.getToken(userId, password);

        mockMvc.perform(MockMvcRequestBuilders.get(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    TypeReference<CommonResponse<UserInfoResponse>> typeRef
                            = new TypeReference<CommonResponse<UserInfoResponse>>() {
                    };

                    CommonResponse<UserInfoResponse> res = objectMapper.readValue(response.getContentAsString(), typeRef);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                    assertThat(res.getData().getUserId()).isEqualTo(userId);
                    assertThat(res.getData().getChildren()).hasSize(2);
                    assertThat(res.getData().getRequirements()).isEqualTo("요구사항");
                    assertThat(res.getData().getMaxChildAge()).isEqualTo(5);
                });
    }
}
