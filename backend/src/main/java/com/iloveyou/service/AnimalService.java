package com.iloveyou.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;
import com.iloveyou.controller.AnimalController;


@Service
public class AnimalService {
    @Autowired
    AnimalRepository repository;

    public List<Animal> getAllAnimals(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Animal> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Animal>();
        }
    }

    public Animal getAnimalByTag(String tag) {
        Optional<Animal> animal = repository.findByTag(tag);

        if (animal.isPresent()) {
            return animal.get();
        } else {
            // need to handle
        }
    }
}