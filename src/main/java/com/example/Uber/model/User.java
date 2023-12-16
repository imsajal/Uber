package com.example.Uber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class User {


    private String id;
    @Setter private String name;
    @Setter private String phoneNumber;
    @Setter private String email;


    // other metadata
}
