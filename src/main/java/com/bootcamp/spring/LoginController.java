package com.bootcamp.spring;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class LoginController {
    private static final String OTP = "1234";
    private static final String EMAIL = "user@bootcamp.io";
    private static final String LOGIN_SUCCESS = "Successfully logged in";
    private static final String USER_NOT_FOUND = "User not found or invalid credentials";

    /**
     * This method will return string response with the username and
     * password that was passed over the request body as application/json.
     *
     * @param credentials Request body
     * @return  String response
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody Credentials credentials) {

        return "Your username is " + credentials.getUsername()
                + " and your password is " + credentials.getPassword();
    }


    /**
     * This endpoint will take email and otp as query parameters.
     * If the required is set to true and the specific query parameter
     * is not passed by the user in the API it will send a Bad Request error.
     *
     * @param email Email query parameter
     * @param otp OTP query parameter
     * @return String response
     */
    @GetMapping("/verify")
    public String verifyOTP(@RequestParam(name = "email") String email,
                            @RequestParam(name = "otp") String otp) {

        if (!EMAIL.equals(email) || !OTP.equals(otp)) {
            return USER_NOT_FOUND;
        }
        return LOGIN_SUCCESS;
    }

    /**
     * This endpoint will take email as path parameter and OTP as
     * query parameter.
     *
     * @param email Email query parameter
     * @param otp OTP query parameter
     * @return String response
     */
    @GetMapping("/verify/{email}")
    public String verifyOTPPathParam(@PathVariable(name = "email") String email,
                                     @RequestParam(name = "otp") String otp) {

        if (!EMAIL.equals(email) || !OTP.equals(otp)) {
            return USER_NOT_FOUND;
        }
        return LOGIN_SUCCESS;
    }
}
