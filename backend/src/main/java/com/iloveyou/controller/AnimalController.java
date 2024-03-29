package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import com.iloveyou.service.AnimalService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/animals")
@RestController
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AnimalService animalService;

    // Partial search of Animals based on the given field
    @GetMapping("/search/{field}")
    @ResponseBody
    public ResponseEntity<?> getAnimalBySearch(@PathVariable String field, @RequestParam String query) {
        List<Animal> animals = new ArrayList<Animal>();

        // Execute SQL code in the repository depending on what field was given
        if (field.equals("name")) {
            // search by name
            animals = animalRepository.searchByName(query);
        } else if (field.equals("tag")) {
            // search by tag
            animals = animalRepository.searchByTag(query);
        }

        if (animals.isEmpty()) {
            return ResponseEntity.notFound().build();
            //error 404 
        }

        return ResponseEntity.ok().body(animals);
    }

    // Partial search of Animal names or Animal tags
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> getAnimalByNameOrTag(@RequestParam String query) {
        List<Animal> animals = animalRepository.searchByNameOrTag(query);

       if (animals.isEmpty()) {
            return ResponseEntity.notFound().build();
            //error 404 
        }

        return ResponseEntity.ok().body(animals);
    }

    @GetMapping("/{id}")
    Optional<Animal> getAnimalById(@PathVariable long id) {
        return animalRepository.findById(id);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<Animal> getAnimalByTag(@PathVariable("tag") String tag) {
        Animal entity = animalService.getAnimalByTag(tag);

        return new ResponseEntity<Animal>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping()
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable("id") Long id) {
        animalRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@RequestBody Animal animal, @PathVariable("id") Long id) {
        // find the target animal using the path variable id
        Optional<Animal> targetAnimal = animalRepository.findById(id);

        // check if the animal is empty or not
        if (!targetAnimal.isPresent())
            // if empty, respond with not found
            return ResponseEntity.notFound().build();

        // the target animal exists, update the comment
        // first create an Animal type object
        Animal temp = targetAnimal.get();

        // update all fields, some potentially being unchanged
        temp.setName(animal.getName());
        temp.setSex(animal.getSex());
        temp.setDob(animal.getDob());
        temp.setWeight(animal.getWeight());
        temp.setTag(animal.getTag());
        temp.setBreed(animal.getBreed());
        temp.setNotes(animal.getNotes());
        temp.setFather(animal.getFather());
        temp.setMother(animal.getMother());

        animalRepository.save(temp);
        // return success
        return ResponseEntity.ok().build();
    }

    // @GetMapping
    // public ResponseEntity<List<Animal>> getAllAnimals(
    //         @RequestParam(defaultValue = "0") Integer pageNo,
    //         @RequestParam(defaultValue = "10") Integer pageSize,
    //         @RequestParam(defaultValue = "tag") String sortBy) {
    //     List<Animal> list = animalService.getAllAnimals(pageNo, pageSize, sortBy);

    //     return new ResponseEntity<List<Animal>>(list, new HttpHeaders(), HttpStatus.OK);
    // }

    @GetMapping
    public List<Animal> getAnimals() {
        return animalRepository.findAll();
    }

}