package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.entity.AnimalEntity;
import com.iloveyou.entity.UserEntity;
import com.iloveyou.repository.AnimalRepository;
import java.util.List; 


//@RequestMapping("animal")
@RestController
public class AnimalController {
    @Autowired
    AnimalRepository animalRepository; 
    @GetMapping("/animal/{tag}")
    @ResponseBody
    public String getByTag(@PathVariable String tag) { 
        List<AnimalEntity> animals = animalRepository.findByTag(tag); 
        if(animals.size() == 0) {
            return "No animal found with tag: " + tag;
        }
        return animals.get(0).toString();
    }
    
}
