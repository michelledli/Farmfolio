package com.iloveyou.controller;

import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import com.iloveyou.util.AnimalParentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/parents")
@RestController
public class AnimalParentController {

    @Autowired
    AnimalRepository animalRepository;


    @PostMapping("/add")
    public Animal createAnimalWithParents(@RequestBody AnimalParentRequest request) {
        Animal father = request.getFather();
        Animal mother = request.getMother();
        Animal child = request.getChild();

        animalRepository.save(father);
        animalRepository.save(mother);

        child.setFather(father);
        child.setMother(mother);

        return animalRepository.save(child);
    }

    @PutMapping("/update/{childId}")
    public Animal addNewParentsToExistingChild(@PathVariable Long childId,
                                            @RequestBody AnimalParentRequest request) {
        Animal father = request.getFather();
        Animal mother = request.getMother();

        animalRepository.save(father);
        animalRepository.save(mother);

        Optional<Animal> child = animalRepository.findById(childId);
        child.get().setFather(father);
        child.get().setMother(mother);

        return animalRepository.save(child.get());
    }

    //I don't know if we need this but it's here
    //Get the father from the child's id
//    @GetMapping("/father/{childId}")
//    public Optional<Animal> getFatherOfChildAnimal(@PathVariable Long childId) {
//        Optional<Animal> child = animalRepository.findById(childId);
//        Long fatherId = child.get().getFather().getId();
//        return animalRepository.findById(fatherId);
//    }
}
