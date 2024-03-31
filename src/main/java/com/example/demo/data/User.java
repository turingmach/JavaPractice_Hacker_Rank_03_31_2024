package com.example.demo.data;

import jakarta.validation.constraints.NotEmpty;

public class User {
    @NotEmpty
    private String name;
    @NotEmpty(message = "surname cannot be empty")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
