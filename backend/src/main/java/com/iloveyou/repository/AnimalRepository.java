package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.iloveyou.entity.Animal;
import java.util.List;
import java.util.Optional; 


public interface AnimalRepository extends JpaRepository<Animal, Long> { 
    List<Animal> findAll();
    //Animal findById(Long id);
}