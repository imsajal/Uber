package com.example.Uber.services.impl;

import com.example.Uber.model.User;
import com.example.Uber.services.UserService;
import com.example.Uber.services.exceptions.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    Map<String, User> users = new HashMap<>();

    @Override
    public String createUser(String name, String phone, String email) {

        String id = UUID.randomUUID().toString();

        User user = new User(id, name, phone, email);
        users.put(id,user);

        return id;
    }

    @Override
    public User getUser(String id) {

       if(users.containsKey(id)) return users.get(id);
       else throw new UserNotFoundException();
    }
}
