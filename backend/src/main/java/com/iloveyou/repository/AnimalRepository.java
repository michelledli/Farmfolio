package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;
import com.iloveyou.entity.AnimalEntity;
import java.util.List;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> { 
    List<AnimalEntity> findByTag(String tag);
}