package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.iloveyou.repository.StudentRepository; 
import com.iloveyou.entity.StudentEntity;
import java.util.List;


@RestController
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @GetMapping("/student/{studentId}")
    @ResponseBody
    public String getByStudentId(@PathVariable String studentId) {
        List<StudentEntity> students = studentRepository.findBystudentId(studentId);
        if(students.size() == 0) {
            return "No student found with studentId: " + studentId;
        }
        return students.get(0).toString();
    }
}
