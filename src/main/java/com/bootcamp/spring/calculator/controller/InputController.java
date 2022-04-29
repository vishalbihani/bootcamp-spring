package com.bootcamp.spring.calculator.controller;

import com.bootcamp.spring.basics.exchange.ResponseBody;
import com.bootcamp.spring.calculator.BMICalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InputController {
    private static final String BMI_RESULT_MSG = "Your BMI is: ";

    @Autowired
    BMICalculator bmiCalculator;

    @GetMapping("/calculator/bmi")
    public ResponseEntity<ResponseBody> calculateBMI(@RequestParam(name = "name") String name,
                                                     @RequestParam(name = "height") float height,
                                                     @RequestParam(name = "weight") float weight) {

        float bmi = bmiCalculator.calculateBMI(name, height, weight);
        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), BMI_RESULT_MSG + bmi),
                HttpStatus.OK
        );
    }

    @GetMapping("/bmi/list")
    public ResponseEntity<Object> getUserBMIList() {
        return new ResponseEntity<>(
                new ResponseBody(HttpStatus.OK.value(), bmiCalculator.getUserBMIList()),
                HttpStatus.OK
        );
    }
}
