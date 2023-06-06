package com.mfort.user.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfort.user.jwt.JwtToken;
import com.mfort.user.model.request.LoginRequest;
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
public class LoginApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    TestUtils testUtils;
    private final String path = "/login";

    @Test
    public void login_success() throws Exception {

        String userId = testUtils.getRandomUserId();
        String password = "qwer1234!";

        testUtils.createSitter(userId, password);

        LoginRequest request = LoginRequest.builder()
                .userId(userId)
                .password(password)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    JwtToken jwtToken = objectMapper.readValue(response.getContentAsString(), JwtToken.class);

                    assertThat(jwtToken).isNotNull();
                    assertThat(jwtToken.getAccessToken()).isNotNull();
                    assertThat(jwtToken.getRefreshToken()).isNotNull();
                });
    }

    @Test
    public void login_invalid_account_return_unauthorized() throws Exception {

        String userId = "invalidUserId";

        LoginRequest request = LoginRequest.builder()
                .userId(userId)
                .password("qwer1234!")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();

                    assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
                });
    }
}
