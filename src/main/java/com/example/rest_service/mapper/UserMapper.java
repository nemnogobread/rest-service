package com.example.rest_service.mapper;

import com.example.rest_service.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public String convertUsersToJson(List<User> users) throws JsonProcessingException {
        Gson gson = new Gson();
        return gson.toJson(users);
    }
}