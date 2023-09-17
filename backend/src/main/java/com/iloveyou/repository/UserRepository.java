package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;

import com.iloveyou.entity.UserEntity;

/**
 * User repository interface that includes default CRUD operations.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

/**
 * Custom User Repository operations can be added below.
 */

}