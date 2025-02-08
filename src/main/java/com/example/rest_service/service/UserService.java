package com.example.rest_service.service;

import com.example.rest_service.entity.User;
import com.example.rest_service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUserList(){
        //logger.info(userRepository.findAll().toString());
        return userRepository.findAll();
    }

    public User addUser(User user){
        Optional<User> optionalUser = userRepository.findByLastName(user.getLastName());
        if (optionalUser.isEmpty()){
            //logger.info(optionalUser.get().toString());
            return userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("haha user with this lastname is already exists");
        }
    }

    public void deleteUser(User user){
        Optional<User> optionalUser = userRepository.findByLastName(user.getLastName());
        if (optionalUser.isPresent()){
            logger.info(optionalUser.get().toString());
            userRepository.delete(optionalUser.get());
        }
        else {
            throw new EntityNotFoundException("haha this user doesn't exist");
        }
    }

    public User upateUser(User user) {
        Optional<User> optionalUser = userRepository.findByLastName(user.getLastName());
        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            if (user.getFirstName() != null){
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getAge() != null){
                existingUser.setAge(user.getAge());
            }

            logger.info(existingUser.toString());
            return userRepository.save(existingUser);
        }
        else {
            throw new EntityNotFoundException("haha this user doesn't exist");
        }
    }
}
