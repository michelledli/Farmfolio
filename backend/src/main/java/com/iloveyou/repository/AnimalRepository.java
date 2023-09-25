package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;
import com.iloveyou.entity.Animal;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByTag(String tag);

    //sort by tag
    Pageable sortedByTag =
            PageRequest.of(0, 20, Sort.by("tag"));
    Pageable sortedByTag =
            PageRequest.of(0, 20, Sort.by("tag").descending());
    //sort by weight
    Pageable sortedByWeight=
            PageRequest.of(0, 20, Sort.by("weight"));
    Pageable sortedByWeightDesc =
            PageRequest.of(0, 20, Sort.by("weight").descending());
    //sort by name
    Pageable sortedByName =
            PageRequest.of(0, 20, Sort.by("name"));
    Pageable sortedByNameDesc =
            PageRequest.of(0, 20, Sort.by("name").descending());
    Pageable sortedByNameWeightDesc =
            PageRequest.of(0, 20, Sort.by("name").and(Sort.by("weight")).descending());
    //sort by dob
    Pageable sortedByDob =
            PageRequest.of(0, 20, Sort.by("dob"));
    Pageable sortedByDobDesc =
            PageRequest.of(0,20, Sort.by("dob").descending());
    //sort by breed
    Pageable sortedByBreed =
            PageRequest.of(0, 20, Sort.by("breed"));
    Pageable sortedByBreedAndDob =
            PageRequest.of(0, 20, Sort.by("breed").and(Sort.by("dob")));
}