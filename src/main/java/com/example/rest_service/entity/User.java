package com.example.rest_service.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private final int id;
    private String firstName;
    private String lastName;
    private int age;

    public User(int id) {
        this.id = id;
    }
}
