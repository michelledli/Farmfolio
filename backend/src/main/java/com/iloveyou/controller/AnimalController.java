package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional; 

 
@RequestMapping("/animals")
@RestController 
public class AnimalController {
    @Autowired
    AnimalRepository animalRepository;  


    @GetMapping("/all")
    public List<Animal> getAllAnimals(){
        return animalRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Animal> getAnimal(@PathVariable long id) {
        return animalRepository.findById(id);
    } 

    @PostMapping("/add")
    public Animal createAnimal(@RequestBody Animal animal){
        return animalRepository.save(animal); 
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAnimal(@PathVariable("id") Long id){
        animalRepository.deleteById(id);
    } 

    @PutMapping("/update/{id}") 
    public ResponseEntity<Object> updateAnimal(@RequestBody Animal animal, @PathVariable Long id){
        Optional<Animal> animalOptional = animalRepository.findById(animal.getId());
        if(!animalOptional.isPresent())
            return ResponseEntity.notFound().build();
        animal.setId(id);
        animalRepository.save(animal);
        return ResponseEntity.noContent().build();
    }
}