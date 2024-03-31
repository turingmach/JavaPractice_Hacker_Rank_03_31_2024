package com.example.demo.controller;

import com.example.demo.data.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    {
        System.out.println("User Controller ");
    }
    @PostMapping("/users")
    public void create(@RequestBody @Valid User user) {
        System.out.println("name: "+user.getName() + " surname: "+user.getSurname());
    }
}
