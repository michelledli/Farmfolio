package com.iloveyou.repository;

import org.springframework.data.repository.CrudRepository;

import com.iloveyou.entity.StaffEntity;

/**
 * Staff repository interface that includes default CRUD operations.
 */
public interface StaffRepository extends CrudRepository<StaffEntity, Long> {
    
/**
 * Custom User Repository operations can be added below.
 */

  StaffEntity findStaffUserById(String Id);
}
