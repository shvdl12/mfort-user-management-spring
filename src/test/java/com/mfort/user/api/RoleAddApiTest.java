package com.mfort.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.model.request.RegisterParentRequest;
import com.mfort.user.model.request.RegisterSitterRequest;
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
@Transactional
public class RoleAddApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TestUtils testUtils;
    private final String path = "/user/register";

    @Test
    public void register_sitter_return_success() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createParent(userId, password);
        String token = testUtils.getToken(userId, password);

        RegisterSitterRequest request = RegisterSitterRequest.builder()
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

                    CommonResponse res = objectMapper.readValue(response.getContentAsString(), CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                });
    }

    @Test
    public void register_sitter_return_already_registered_error() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createSitter(userId, password);
        String token = testUtils.getToken(userId, password);

        RegisterSitterRequest request = RegisterSitterRequest.builder()
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

                    CommonResponse res = objectMapper.readValue(response.getContentAsString(), CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.ALREADY_REGISTERED_SITTER.getCode());
                });
    }

    @Test
    public void register_parent_return_success() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createSitter(userId, password);
        String token = testUtils.getToken(userId, password);

        RegisterParentRequest request = RegisterParentRequest.builder()
                .children(testUtils.getChildren())
                .requirements("요리가 가능한 분")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path + "/parent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    CommonResponse res = objectMapper.readValue(response.getContentAsString(), CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                });
    }

    @Test
    public void register_parent_return_already_registered_error() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createParent(userId, password);
        String token = testUtils.getToken(userId, password);

        RegisterParentRequest request = RegisterParentRequest.builder()
                .children(testUtils.getChildren())
                .requirements("요리가 가능한 분")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path + "/parent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "Bearer " + token))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    CommonResponse res = objectMapper.readValue(response.getContentAsString(), CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(res.getCode()).isEqualTo(ResponseCode.ALREADY_REGISTERED_PARENT.getCode());
                });
    }
}
