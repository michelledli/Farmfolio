package com.iloveyou;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
		// hard coded users made for testing
		Account u1 =
			Account.builder().fname("Matt").lname("Tai").email("mt9123@gmail.com").password("password").build();
		Account u2 =
			Account.builder().fname("Pat").lname("Lai").email("patlai@csus.edu").password("password").build();
		Account u3 =
			Account.builder().fname("Kat").lname("Shai").email("kshai916@hotmail.com").password("password").build();

		Account userEntity =
			Account.builder().fname("fname").lname("lname").email("user").password("a").isAdmin(false).build();

		Account adminEntity =
			Account.builder().fname("fname").lname("lname").email("admin").password("a").isAdmin(true).build();

		List<Account> accounts = Arrays.asList(u1, u2, u3, adminEntity, userEntity);
		this.accountRepository.saveAll(accounts);


		// hard coded animals made for testing
		Animal a1 = 
			Animal.builder().name("Penny").dob(new Date()).weight(80).tag("x").breed("a").build();
		Animal a2 = 
			Animal.builder().name("Kenny").dob(new Date()).weight(100).tag("z").breed("b").build(); 
		Animal a3 = 
			Animal.builder().name("Benny").dob(new Date()).weight(75).tag("y").breed("b").build(); 
		Animal a4 = 
			Animal.builder().name("Jenny").dob(new Date()).weight(120).tag("x").breed("a").build(); 
		Animal a5 = 
			Animal.builder().name("Denny").dob(new Date()).weight(135).tag("y").breed("c").build(); 

		List<Animal> animals = Arrays.asList(a1, a2, a3, a4, a5);
		this.animalRepository.saveAll(animals);
	}  
}