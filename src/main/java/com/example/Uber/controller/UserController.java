package com.example.Uber.controller;

import com.example.Uber.model.User;
import com.example.Uber.services.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserController {

    private UserService userService;

    public String createUser(String  name, String phone, String email){
        String userId = userService.createUser(name , phone, email);
        return userId;
    }
}
