package com.bootcamp.spring.calculator;

import org.springframework.stereotype.Service;

@Service
public class BMICalculator {

    /**
     * Calculate BMI of the body.
     *
     * @param height Height in metres
     * @param weight Weight in Kgs
     * @return BMI of the body
     */
    public float calculateBMI(float height, float weight) {
        /*
        Formula to calculate BMI is:
            BMI = weight (in kilograms) / (height (in metre))^2    kg/m^2
         */

        sanityCheck(height, weight);
        return (float) (weight / Math.pow(height, 2));
    }

    /**
     * Checking validity of the data.
     *
     * @param height Height in metres, should be greater than zero
     * @param weight Weight in Kgs, should be greater than zero
     */
    private void sanityCheck(float height, float weight) {
        if (weight > 0 && height > 0) {
            return;
        }
        throw new IllegalArgumentException("Invalid height or weight value");
    }
}
