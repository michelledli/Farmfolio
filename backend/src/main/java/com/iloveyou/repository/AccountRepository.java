package com.iloveyou.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iloveyou.entity.Account;


/**
 * User repository interface that includes default CRUD operations.
 */
public interface AccountRepository extends JpaRepository<Account, Long>  {
	List<Account> findAll();
}