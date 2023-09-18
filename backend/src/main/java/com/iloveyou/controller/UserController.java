package com.iloveyou.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.repository.UserRepository;
import com.iloveyou.entity.AnimalEntity;
import com.iloveyou.entity.UserEntity;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user/{userId}")
    @ResponseBody
    public String getUserById(@PathVariable Long userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isPresent() == false) {
            return "No user found with the provided Id: " + userId;
        }
        return user.get().toString();
    }
}
