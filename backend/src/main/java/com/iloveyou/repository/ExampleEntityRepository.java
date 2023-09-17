package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;

import com.iloveyou.entity.ExampleEntity;

public interface ExampleEntityRepository extends CrudRepository<ExampleEntity, Long> {
}