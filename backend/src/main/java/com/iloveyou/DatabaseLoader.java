package com.iloveyou;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.UserEntity;
import com.iloveyou.repository.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepository;

	private UserEntity userEntity =
			UserEntity.builder().fname("Fred").lname("Martin").email("f.martin@gmail" +
					".com").username("f.martin").password("password123").build();

	@Autowired
	public DatabaseLoader(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.userRepository.save(userEntity);
	}
}