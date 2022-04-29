package com.bootcamp.spring.calculator.dto;

public class UserBMI {

    private final String name;

    private final float height;

    private final float weight;

    private final float bmi;

    public UserBMI(String name, float height, float weight, float bmi) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
    }

    public String getName() {
        return name;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public float getBmi() {
        return bmi;
    }
}
