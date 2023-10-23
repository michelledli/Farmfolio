package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;

import com.iloveyou.entity.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {}
