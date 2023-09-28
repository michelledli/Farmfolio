package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import com.iloveyou.entity.Animal;
import java.util.List;
import java.util.Optional; 

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

    List<Animal> findAll();
}