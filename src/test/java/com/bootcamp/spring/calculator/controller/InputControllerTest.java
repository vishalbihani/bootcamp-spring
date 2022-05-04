package com.bootcamp.spring.calculator.controller;

import com.bootcamp.spring.calculator.BMICalculator;
import com.bootcamp.spring.calculator.dto.UserBMI;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(InputController.class)
public class InputControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BMICalculator calculator;

    /*
    POST call to /calculator/bmi should return 18.0 as the BMI result.
     */
    @Test
    public void calculateBmiShouldReturnResult() throws Exception {
        // Mocking the calculateBMI method
        Mockito.when(calculator.calculateBMI(Mockito.anyString(), Mockito.anyFloat(), Mockito.anyFloat()))
                .thenReturn((float) 18.0);

        // Performing the API request
        this.mvc.perform(
                MockMvcRequestBuilders.get("/calculator/bmi")
                        .queryParam("name", "Thanos")
                        .queryParam("height", "1.7")
                        .queryParam("weight", "52")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "message", CoreMatchers.containsString("18.0")
                ));
    }

    /*
    GET call to /bmi/list should return list of users with their name, height, weight, and bmi.
    The order should be maintained.
     */
    @Test
    public void getUserBMIListShouldReturnListOfUsersWithBMI() throws Exception {
        UserBMI grogu = new UserBMI("Grogu", 160, 40, (float) 18.0);
        UserBMI groot = new UserBMI("Groot", 170, 50, (float) 19.0);
        UserBMI loki = new UserBMI("Loki", 180, 60, (float) 20.0);
        UserBMI mandalore = new UserBMI("Mandalore", 190, 70, (float) 21.0);

        List<UserBMI> userBMIList = new ArrayList<>();
        userBMIList.add(grogu);
        userBMIList.add(groot);
        userBMIList.add(loki);
        userBMIList.add(mandalore);

        // Mocking the getUserBMIList method.
        Mockito.when(calculator.getUserBMIList()).thenReturn(userBMIList);

        this.mvc.perform(MockMvcRequestBuilders.get("/bmi/list").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("message", IsCollectionWithSize.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("message[0].name", CoreMatchers.is("Grogu")))
                .andExpect(MockMvcResultMatchers.jsonPath("message[1].name", CoreMatchers.is("Groot")))
                .andExpect(MockMvcResultMatchers.jsonPath("message[2].name", CoreMatchers.is("Loki")))
                .andExpect(MockMvcResultMatchers.jsonPath("message[3].name", CoreMatchers.is("Mandalore")));

    }
}
