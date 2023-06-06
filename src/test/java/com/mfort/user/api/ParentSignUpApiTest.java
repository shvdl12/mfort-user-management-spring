package com.mfort.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
import com.mfort.user.model.request.ParentSignUpRequest;
import com.mfort.user.model.request.SitterSignUpRequest;
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
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ParentSignUpApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TestUtils testUtils;
    private final String path = "/user/signup/parent";


    @Test
    public void parent_signup_return_success() throws Exception {

        String userId = testUtils.getRandomUserId();

        ParentSignUpRequest request = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .children(testUtils.getChildren())
                .requirements("요구사항")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    CommonResponse commonResponse = objectMapper.readValue(response.getContentAsString()
                            , CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(commonResponse.getCode()).isEqualTo(ResponseCode.SUCCESS.getCode());
                });
    }

    @Test
    public void signup_return_missing_children_error() throws Exception {

        String userId = testUtils.getRandomUserId();

        ParentSignUpRequest request = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
//                .children(testUtils.getChildren())
                .requirements("요구사항")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    CommonResponse commonResponse = objectMapper.readValue(response.getContentAsString()
                            , CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(commonResponse.getCode()).isEqualTo(ResponseCode.INVALID_PARAMETER.getCode());
                    assertThat(commonResponse.getMessage()).isEqualTo("아이 정보를 입력해주세요.");
                });
    }

    @Test
    public void signup_return_empty_children_error() throws Exception {

        String userId = testUtils.getRandomUserId();

        ParentSignUpRequest request = ParentSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .children(new ArrayList<>())
                .requirements("요구사항")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    CommonResponse commonResponse = objectMapper.readValue(response.getContentAsString()
                            , CommonResponse.class);

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
                    assertThat(commonResponse.getCode()).isEqualTo(ResponseCode.INVALID_PARAMETER.getCode());
                    assertThat(commonResponse.getMessage()).isEqualTo("아이 정보를 입력해주세요.");
                });
    }
}
