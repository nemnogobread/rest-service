package com.example.rest_service.service;

import com.example.rest_service.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<String> getUserList(){
        return List.of((
                new User(
                        2,
                        "Igor",
                        "Miloslavov",
                        27)
        ).toString());
    }
}
