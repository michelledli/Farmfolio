package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.repository.StaffRepository;
import com.iloveyou.repository.StudentRepository;
import com.iloveyou.repository.UserRepository;
import com.iloveyou.entity.StaffEntity;
import com.iloveyou.entity.StudentEntity;
import com.iloveyou.entity.UserEntity;
import java.util.List;
import java.util.Optional;

@RestController
public class StaffController {
    @Autowired
    StaffRepository staffRepository;
    @GetMapping("/user/{userId}")
    @ResponseBody
    public String getStaffUserById(@PathVariable String Id) {
        StaffEntity user = staffRepository.findStaffUserById(Id);   // Should return null if user is not a staff member.
        if(user == null) {
            return "No Staff user found with the provided Id: " + Id;
        }
        return user.toString();
    }
}
