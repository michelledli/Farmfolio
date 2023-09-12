package com.iloveyou;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.ExampleEntity;
import com.iloveyou.repository.ExampleEntityRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final ExampleEntityRepository repository;

	@Autowired
	public DatabaseLoader(ExampleEntityRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... strings) throws Exception {
		this.repository.save(new ExampleEntity("Example", 999L));
	}
}