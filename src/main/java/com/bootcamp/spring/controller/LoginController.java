package com.bootcamp.spring.controller;

import com.bootcamp.spring.UserNotFoundException;
import com.bootcamp.spring.dto.Credentials;
import com.bootcamp.spring.exchange.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private static final int STATUS_OK = 200;
    public static final int STATUS_NOT_FOUND = 404;
    private static final String OTP = "1234";
    private static final String EMAIL = "user@bootcamp.io";
    private static final String LOGIN_SUCCESS = "Successfully logged in";
    public static final String USER_NOT_FOUND = "User not found or invalid credentials";

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
     * @return Response body
     */
    @GetMapping("/verify")
    public ResponseEntity<ResponseBody> verifyOTP(@RequestParam(name = "email") String email,
                                    @RequestParam(name = "otp") String otp) throws UserNotFoundException {

        if (!isOtpValid(email, otp)) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(
                new ResponseBody(STATUS_OK, LOGIN_SUCCESS), HttpStatus.OK
        );
    }

    /**
     * This endpoint will take email as path parameter and OTP as
     * query parameter.
     *
     * @param email Email query parameter
     * @param otp OTP query parameter
     * @return Response body
     */
    @GetMapping("/verify/{email}")
    public ResponseEntity<ResponseBody> verifyOTPPathParam(@PathVariable(name = "email") String email,
                                     @RequestParam(name = "otp") String otp) throws UserNotFoundException {

        if (!isOtpValid(email, otp)) {
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return new ResponseEntity<>(
                new ResponseBody(STATUS_OK, LOGIN_SUCCESS), HttpStatus.OK
        );
    }

    private boolean isOtpValid(String email, String otp) {
        return EMAIL.equals(email) && OTP.equals(otp);
    }
}
