package com.example.demo.data;

import jakarta.validation.constraints.NotEmpty;

public class UserV2 {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    public UserV2(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public UserV2(UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.surname = userBuilder.surname;
    }

    public UserV2() {

    }

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

    public static class UserBuilder {
        @NotEmpty
        private String name;
        @NotEmpty
        private String surname;

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserV2 build(){
            return new UserV2(this);
        }

    }
}
