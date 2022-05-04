package com.bootcamp.spring.basics;

import com.bootcamp.spring.basics.controller.LoginController;
import com.bootcamp.spring.basics.dto.Credentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LoginController.class)
public class LoginControllerTest {
    private final String email = "user@bootcamp.io";
    private final String password = "password";
    private final String otp = "1234";

    @Autowired
    private MockMvc mvc;

    private static ObjectMapper mapper;

    /*
    @BeforeAll should be used to initialize anything before executing tests.
     */
    @BeforeAll
    public static void init() {
        mapper = new ObjectMapper();
    }

    /*
    POST call to /login endpoint with credentials should return a string containing the credentials.
     */
    @Test
    public void loginShouldReturnCredentials() throws Exception {
        Credentials credentials = new Credentials(email, password);

        this.mvc.perform(
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsBytes(credentials))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(email)))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(password)));
    }

    /*
    POST call to /verify/{email} with incorrect OTP should return string containing "invalid credentials"
     */
    @Test
    public void verifyWithPathParamWithIncorrectOtpShouldReturnInvalidCredentials() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get("/verify/" + email) // Email in path param
                        .queryParam("otp", "4321")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(
                        "invalid credentials"
                )));
    }

    /*
    POST call to /verify with email as query param with incorrect OTP should return string containing
    "invalid credentials"
     */
    @Test
    public void verifyWithQueryParamWithIncorrectOtpShouldReturnInvalidCredentials() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders.get("/verify")
                                .queryParam("email", email) // Email in query param
                                .queryParam("otp", "4321")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(
                        "invalid credentials"
                )));
    }

    /*
    POST call to /verify/{email} should with incorrect email should return string containing "User not found"
     */
    @Test
    public void verifyWithPathParamWithIncorrectEmailShouldReturnUserNotFound() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders.get("/verify/incorrect@email.com")
                                .queryParam("otp", otp)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(
                        "User not found"
                )));
    }

    /*
    POST call to /verify with email as query param with incorrect email should return string containing
    "User not found"
     */
    @Test
    public void verifyWithQueryParamWithIncorrectEmailShouldReturnUserNotFound() throws Exception {
        this.mvc.perform(
                        MockMvcRequestBuilders.get("/verify")
                                .queryParam("email", "incorrect@email.com")
                                .queryParam("otp", otp)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(
                        "User not found"
                )));
    }
}
