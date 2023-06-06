package com.mfort.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.common.model.CommonResponse;
import com.mfort.user.common.model.ResponseCode;
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

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class SitterSignUpApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TestUtils testUtils;
    private final String path = "/user/signup/sitter";

    @Test
    public void sitter_signup_return_success() throws Exception {

        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
    public void signup_return_missing_name_error() throws Exception {
        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
//                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("이름을 입력해주세요.");
                });
    }

    @Test
    public void signup_return_missing_birthAt_error() throws Exception {
        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
//                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("생일을 입력해주세요.");
                });
    }

    @Test
    public void signup_return_missing_gender_error() throws Exception {
        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
//                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("성별을 입력해주세요.");
                });
    }

    @Test
    public void signup_return_invalid_gender_error() throws Exception {
        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("남자")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("성별은 Female, Male 중 하나 이여야 합니다.");
                });
    }

    @Test
    public void signup_return_missing_email_error() throws Exception {
        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
//                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("이메일을 입력해주세요.");
                });
    }

    @Test
    public void signup_return_invalid_email_error() throws Exception {
        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("이메일 형식이 올바르지 않습니다.");
                });
    }

    @Test
    public void signup_return_missing_userId_error() throws Exception {

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
//                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("아이디를 입력해주세요.");
                });
    }

    @Test
    public void signup_return_missing_password_error() throws Exception {

        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
//                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("비밀번호를 입력해주세요.");
                });
    }

    @Test
    public void signup_return_invalid_password_error() throws Exception {

        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("password")
                .email("test001@gmail.com")
                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage())
                            .isEqualTo("비밀번호는 숫자, 문자, 특수 문자를 포함한 8자리 이상 이어야 합니다.");
                });
    }

    @Test
    public void signup_return_missing_minChildAge_error() throws Exception {

        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
//                .minChildAge(3)
                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("최소 아이 연령을 입력해주세요.");
                });
    }

    @Test
    public void signup_return_missing_maxChildAge_error() throws Exception {

        String userId = testUtils.getRandomUserId();

        SitterSignUpRequest request = SitterSignUpRequest.builder()
                .name("홍길동")
                .birthAt(LocalDate.of(1990, 1, 1))
                .gender("Male")
                .userId(userId)
                .password("qwer1234!")
                .email("test001@gmail.com")
                .minChildAge(3)
//                .maxChildAge(5)
                .bio("자기소개")
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
                    assertThat(commonResponse.getMessage()).isEqualTo("최대 아이 연령을 입력해주세요.");
                });
    }
}
