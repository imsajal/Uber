package com.example.Uber.services;

import com.example.Uber.model.User;

public interface UserService {
    String createUser(String name, String phone, String email);
    User getUser(String id);
}
