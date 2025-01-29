package com.example.rest_service.controller;

import com.example.rest_service.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/homepage")
    public List<String> hello(){
        return List.of((new User(2, "Igor", "Miloslavov", 27)).toString());
    }
}
