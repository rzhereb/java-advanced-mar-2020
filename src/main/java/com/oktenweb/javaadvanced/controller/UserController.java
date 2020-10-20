package com.oktenweb.javaadvanced.controller;

import com.oktenweb.javaadvanced.entity.User;
import com.oktenweb.javaadvanced.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

// Цей контролер не має жодного відношення до автентифікації. Нею повністю займається Spring

public class UserController {

    @Autowired
    private IUserService userService;

    // реєстрація юзера
    @PostMapping
    public String registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
