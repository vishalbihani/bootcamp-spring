package com.bootcamp.spring.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BMICalculatorTest {

    private static BMICalculator calculator;

    @BeforeAll
    public static void init() {
        calculator = new BMICalculator();
    }

    @Test
    public void calculateBMIWithValidValueShouldReturnCorrectBMI() {
        float bmi = calculator.calculateBMI("Steve", (float) 1.7, (float) 52);

        Assertions.assertEquals((float) 17.993078, bmi);
    }

    @Test
    public void calculateBMIWithInvalidValueShouldThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                calculator.calculateBMI("Jack", (float) 1.8, (float) -1));
    }
}
