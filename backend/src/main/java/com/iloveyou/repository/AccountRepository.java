package com.iloveyou.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iloveyou.entity.Account;

/**
 * User repository interface that includes default CRUD operations.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
	List<Account> findByEmailAndPassword(String username, String password);
}