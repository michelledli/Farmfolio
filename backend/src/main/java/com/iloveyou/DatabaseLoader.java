package com.iloveyou;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.Animal;
import com.iloveyou.entity.Account;
import com.iloveyou.repository.AnimalRepository;
import com.iloveyou.repository.AccountRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final AccountRepository accountRepository; 
	private final AnimalRepository animalRepository;

	@Autowired
	public DatabaseLoader(AccountRepository accountRepository, AnimalRepository animalRepository) {
		this.accountRepository = accountRepository;
		this.animalRepository = animalRepository;
		
	}

	@Override
	public void run(String... strings) throws Exception {
		Account userEntity =
			Account.builder().fname("fname").lname("lname").email("email").password("password").build();

		this.accountRepository.save(userEntity);

		Animal animalEntity = 
			Animal.builder().name("name").dob(new Date()).weight(1).tag("tag").breed("breed").build(); 
		
		System.out.println(animalEntity.toString());

		this.animalRepository.save(animalEntity);
	}  
}