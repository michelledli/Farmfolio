package com.iloveyou.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import com.iloveyou.entity.Account;

/**
 * User repository interface that includes default CRUD operations.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
	List<Account> findByEmailAndPassword(String username, String password);

    // Search for an Account by name or email
    @Query("SELECT a FROM Account a WHERE "
    + "LOWER(a.fname) LIKE CONCAT('%', LOWER(?1),'%') OR "
    + "LOWER(a.lname) LIKE CONCAT('%', LOWER(?1),'%') OR "
	+ "LOWER(a.email) LIKE CONCAT('%', LOWER(?1),'%')")
    List<Account> searchByNameOrEmail(String query);

	// Search for an Account by name (first or last)
	@Query("SELECT a FROM Account a WHERE "
    + "LOWER(a.fname) LIKE CONCAT('%', LOWER(?1),'%') OR "
    + "LOWER(a.lname) LIKE CONCAT('%', LOWER(?1),'%')")
	List<Account> searchByName(String query);

	// Search for an Account by email
	@Query("SELECT a FROM Account a WHERE "
    + "LOWER(a.email) LIKE CONCAT('%', LOWER(?1),'%')")
	List<Account> searchByEmail(String query);

	List<Account> findAll();
}