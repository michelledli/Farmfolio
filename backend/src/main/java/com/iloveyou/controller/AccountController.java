package com.iloveyou.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iloveyou.repository.AccountRepository;
import com.iloveyou.entity.Animal;
import com.iloveyou.entity.Account;

@Controller
public class AccountController {
    // Not be needed anymore

    // @Autowired
    // AccountRepository userRepository;
    // @GetMapping("/user/{userId}")
    // @ResponseBody
    // public String getUserById(@PathVariable Long userId) {
    //     Optional<Account> user = userRepository.findById(userId);
    //     if(user.isPresent() == false) {
    //         return "No user found with the provided Id: " + userId;
    //     }
    //     return user.get().toString();
    // }
}
