package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iloveyou.entity.Animal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface AnimalRepository extends JpaRepository<Animal, Long> { 

	// Search for an Animal by name 
	@Query("SELECT a FROM Animal a WHERE "
    + "LOWER(a.name) LIKE CONCAT('%', LOWER(?1),'%')")
	List<Animal> searchByName(String query);

	// Search for an Animal by tag 
	@Query("SELECT a FROM Animal a WHERE "
    + "LOWER(a.tag) LIKE CONCAT('%', LOWER(?1),'%')")
	List<Animal> searchByTag(String query);

    // Search for an Animal by name or Tag
    @Query("SELECT a FROM Animal a WHERE "
    + "LOWER(a.name) LIKE CONCAT('%', LOWER(?1),'%') OR "
    + "LOWER(a.tag) LIKE CONCAT('%', LOWER(?1),'%')")
    List<Animal> searchByNameOrTag(String query);

    List<Animal> findByTag(String tag);

    int numDisplayed = 20;
    //sort by tag
    Pageable sortedByTag =
            PageRequest.of(0, numDisplayed, Sort.by("tag"));
    Pageable sortedByTagDesc =
            PageRequest.of(0, numDisplayed, Sort.by("tag").descending());
    //sort by weight
    Pageable sortedByWeight=
            PageRequest.of(0, numDisplayed, Sort.by("weight"));
    Pageable sortedByWeightDesc =
            PageRequest.of(0, numDisplayed, Sort.by("weight").descending());
    //sort by name
    Pageable sortedByName =
            PageRequest.of(0, numDisplayed, Sort.by("name"));
    Pageable sortedByNameDesc =
            PageRequest.of(0, numDisplayed, Sort.by("name").descending());
    Pageable sortedByNameWeightDesc =
            PageRequest.of(0, numDisplayed, Sort.by("name").and(Sort.by("weight")).descending());
    //sort by dob
    Pageable sortedByDob =
            PageRequest.of(0, numDisplayed, Sort.by("dob"));
    Pageable sortedByDobDesc =
            PageRequest.of(0,numDisplayed, Sort.by("dob").descending());
    //sort by breed
    Pageable sortedByBreed =
            PageRequest.of(0, numDisplayed, Sort.by("breed"));
    Pageable sortedByBreedAndDob =
            PageRequest.of(0, numDisplayed, Sort.by("breed").and(Sort.by("dob")));
    List<Animal> findAll();
}