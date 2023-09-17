package com.iloveyou;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.ExampleEntity;
import com.iloveyou.entity.UserEntity;
import com.iloveyou.repository.ExampleEntityRepository;
import com.iloveyou.repository.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepository;
	private final ExampleEntityRepository exampleEntityRepository;

	@Autowired
	public DatabaseLoader(UserRepository userRepository, ExampleEntityRepository exampleEntityRepository) {
		this.userRepository = userRepository;
		this.exampleEntityRepository = exampleEntityRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		UserEntity userEntity =
			UserEntity.builder().fname("Fred").lname("Martin").email("f.martin@gmail" +
					".com").username("f.martin").password("password123").build();

		this.userRepository.save(userEntity);

		ExampleEntity exampleEntity = new ExampleEntity("Example", 999L);
		this.exampleEntityRepository.save(exampleEntity);
	}
}