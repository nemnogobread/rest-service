package com.example.rest_service.controller;

import com.example.rest_service.entity.User;
import com.example.rest_service.mapper.UserMapper;
import com.example.rest_service.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/api/v1/user-list")
    public String getUserList(){
        List<User> userList = userService.getUserList();
        try {
            return userMapper.convertUsersToJson(userList);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @PostMapping("/api/v1/add-user")
    public User createUser(@RequestBody User user){
        logger.info(user.toString());
        return userService.addUser(user);
    }
}
