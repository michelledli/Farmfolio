package com.iloveyou;

import java.util.Date;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.AnimalEntity;
import com.iloveyou.entity.ExampleEntity;
import com.iloveyou.entity.StudentEntity;
import com.iloveyou.entity.UserEntity;
import com.iloveyou.repository.AnimalRepository;
import com.iloveyou.repository.ExampleEntityRepository;
import com.iloveyou.repository.StudentRepository;
import com.iloveyou.repository.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository userRepository; 
	private final AnimalRepository animalRepository;
	private final ExampleEntityRepository exampleEntityRepository; 
	private final StudentRepository studentRepository;

	@Autowired
	public DatabaseLoader(UserRepository userRepository,AnimalRepository animalRepository, StudentRepository studentRepository, ExampleEntityRepository exampleEntityRepository) {
		this.userRepository = userRepository;
		this.animalRepository = animalRepository;
		this.exampleEntityRepository = exampleEntityRepository;
		this.studentRepository = studentRepository;
		
	}

	@Override
	public void run(String... strings) throws Exception {
		UserEntity userEntity =
			UserEntity.builder().fname("Fred").lname("Martin").email("f.martin@gmail" +
					".com").username("f.martin").password("password123").build();

		this.userRepository.save(userEntity);

		ExampleEntity exampleEntity = new ExampleEntity("Example", 999L);
		this.exampleEntityRepository.save(exampleEntity);  

		AnimalEntity animalEntity = 
			AnimalEntity.builder().name("test").birthDate(new Date()).weight(1000).tag("testTag").breed("Cow").build(); 
			System.out.println(animalEntity.toString());

			this.animalRepository.save(animalEntity);

		StudentEntity studentEntity = 
			StudentEntity.builder().fname("Fred").lname("Martin").email("f.martin@gmail" +
					".com").username("f.martin").password("password123").studentId("12345").build(); 
			System.out.println(studentEntity.toString());
			this.studentRepository.save(studentEntity);
	}  
}