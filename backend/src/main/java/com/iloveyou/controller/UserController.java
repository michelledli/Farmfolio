package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.repository.StudentRepository;
import com.iloveyou.repository.UserRepository;
import com.iloveyou.entity.StudentEntity;
import com.iloveyou.entity.UserEntity;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/user/{userId}")
    @ResponseBody
    public String getUserById(@PathVariable String Id) {
        UserEntity user = userRepository.findUserById(Id);
        if(user == null) {
            return "No user found with the provided Id: " + Id;
        }
        return user.toString();
    }
}
