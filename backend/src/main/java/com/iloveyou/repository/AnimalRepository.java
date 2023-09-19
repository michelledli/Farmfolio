package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;
import com.iloveyou.entity.Animal;
import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long> { 
    List<Animal> findByTag(String tag);
}