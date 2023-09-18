package com.iloveyou.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.repository.StaffRepository;
import com.iloveyou.entity.StaffEntity;

@RestController
public class StaffController {
    @Autowired
    StaffRepository staffRepository;
    @GetMapping("/staff/{userId}")
    @ResponseBody
    public String getStaffUserById(@PathVariable Long userId) {
        Optional<StaffEntity> user = staffRepository.findById(userId);
        if(user.isPresent() == false) {
            return "No Staff user found with the provided Id: " + userId;
        }
        return user.get().toString();
    }
}
