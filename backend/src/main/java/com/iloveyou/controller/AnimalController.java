package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.entity.Account;
import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List; 


@RequestMapping("animals")
@RestController
public class AnimalController {
    @Autowired
    AnimalRepository animalRepository; 

    @PostMapping()
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal); 
    }

    @GetMapping()
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    // Partial search of Animals based on the given field
    @GetMapping("/search/{field}")
    @ResponseBody
    public String getAccountBySearch(@PathVariable String field, @RequestParam String query) {
        List<Animal> animals = new ArrayList<Animal>();

        // Execute SQL code in the repository depending on what field was given
        if (field.equals("name")) {
            // search by name
            animals = animalRepository.searchByName(query);
        } else if (field.equals("tag")) {
            // search by email
            animals = animalRepository.searchByTag(query);
        }

        if (animals.size() == 0) {
            return "No accounts found matching search: " + query;
        }

        return animals.toString();
    }

    // Partial search of Animal names or Animal tags
    @GetMapping("/search")
    @ResponseBody
    public String getAnimalByNameOrTag(@RequestParam String query) {
        List<Animal> animals = animalRepository.searchByNameOrTag(query);

        if (animals.size() == 0) {
            return "No animals found with a name or tag matching: " + query;
        }

        return animals.toString();
    }
}
