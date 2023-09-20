package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import java.util.Date;
import java.util.List; 


@RequestMapping("animals")
@RestController 
public class AnimalController {
    @Autowired
    AnimalRepository animalRepository; 
    @GetMapping("/tag/{tag}")
    @ResponseBody //finds by individual animal tag
    public String getByTag(@PathVariable String tag) { 
        List<Animal> animals = animalRepository.findByTag(tag); 
        if(animals.size() == 0) {
            return "No animal found with tag: " + tag;
        }
        return animals.get(0).toString();
    } 

    @GetMapping("/all")
    @ResponseBody //finds all animals
    public String getAll() { 
        List<Animal> animals = (List<Animal>) animalRepository.findAll(); 
        if(animals.size() == 0) {
            return "No animals found";
        }
        String result = "";
        for(Animal animal : animals) {
            result += animal.toString() + "\n";
        }
        return result;
    }
    
    @GetMapping("/create/{name}/{weight}/{tag}/{breed}")
    @ResponseBody //creates animal
    public String createAnimal(@PathVariable String name,@PathVariable int weight, @PathVariable String tag, @PathVariable String breed) { 
        Animal animal = Animal.builder().name(name).weight(weight).tag(tag).breed(breed).build(); 
        animalRepository.save(animal); 
        return animal.toString();
    } 

    @GetMapping("/update/{name}/{weight}/{tag}/{breed}") //couldnt figure out DATE
    @ResponseBody //updates and finds based off the tag of the animal
    public String updateAnimal(@PathVariable String tag, @PathVariable String name,@PathVariable int weight, @PathVariable String breed) { 
        List<Animal> animals = animalRepository.findByTag(tag); 
        if(animals.size() == 0) {
            return "No animal found with tag: " + tag;
        }
        Animal animal = animals.get(0); 
        animal.setName(name); 
        animal.setWeight(weight); 
        animal.setBreed(breed); 
        animalRepository.save(animal); 
        return animal.toString();
    } 

    //updates tag 
    @GetMapping("/update/{tag}/{newTag}")
    @ResponseBody //updates and finds based off the tag of the animal
    public String updateAnimalTag(@PathVariable String tag, @PathVariable String newTag) { 
        List<Animal> animals = animalRepository.findByTag(tag); 
        if(animals.size() == 0) {
            return "No animal found with tag: " + tag;
        }
        Animal animal = animals.get(0); 
        animal.setTag(newTag); 
        animalRepository.save(animal); 
        return animal.toString();
    }

    @GetMapping("/delete/{tag}")
    @ResponseBody //deletes animal based off tag
    public String deleteAnimal(@PathVariable String tag) { 
        List<Animal> animals = animalRepository.findByTag(tag); 
        if(animals.size() == 0) {
            return "No animal found with tag: " + tag;
        }
        animalRepository.delete(animals.get(0)); 
        return "Deleted animal with tag: " + tag;
    } 
}
