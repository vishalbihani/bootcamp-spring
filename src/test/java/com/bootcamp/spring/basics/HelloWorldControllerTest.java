package com.bootcamp.spring.basics;

import com.bootcamp.spring.basics.controller.HelloWorldController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCallShouldReturnHelloWorld() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders.get("/hello"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello World!")));
    }

    @Test
    public void postCallShouldReturnHelloWorld() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders.post("/hello"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(
                        "Hello! You performed a post request successfully."
                )));
    }

    @Test
    public void postCallWithNameShouldReturnHelloWithName() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders
                        .post("/hello")
                        .contentType(MediaType.TEXT_PLAIN_VALUE)
                        .content("Thanos"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(
                        "Hello Thanos"
                )));
    }
}
