package com.bootcamp.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {

    /**
     * If the name of the variable and the json property is not same
     * then use <code>@JsonProperty(value)</code> to map two different values.
     */

    @JsonProperty("email")
    private String username;

    @JsonProperty("password")
    private String password;

    public Credentials() {}

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
