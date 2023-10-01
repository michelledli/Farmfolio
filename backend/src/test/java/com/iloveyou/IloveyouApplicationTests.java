package com.iloveyou;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.iloveyou.controller.AccountController;
import com.iloveyou.entity.Account;
import com.iloveyou.repository.AccountRepository;
import com.iloveyou.repository.AnimalRepository;

@SpringBootTest
class IloveyouApplicationTests {
	private AccountController accController = new AccountController();

	@Test
	@Disabled
	void makeNewAccountTest() {
		Account testAccount = Account.builder().fname("Test").lname("Bot").email("test.bot@test.com").password("password").build();
		accController.createAccount(testAccount);
		Assert.isTrue(accController.getAccount(testAccount.getId()) != null,"REEEEEEEEEEEEEEEEE");
	}

	@Test
	void accountClassTest() {
		Account testAccount = Account.builder().fname("Test").lname("Bot").email("test.bot@test.com").password("password").build();
		Account testAccount2 = Account.builder().fname("Test").lname("Bot2").email("test.bot2@test.com").password("password").build();
		
		//Assert.isTrue(testAccount.getId() < testAccount2.getId(), "Account constructor does not properly incrament ID's");

		Assert.isTrue(testAccount.getFname() == "Test", "Account constructor builder is not functioning correctly.");
		Assert.isTrue(testAccount2.getEmail()== "test.bot2@test.com", "Account constructor builder is not functioning correctly.");
	}

}
