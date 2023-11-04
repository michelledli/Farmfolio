package com.iloveyou;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iloveyou.entity.Animal;
import com.iloveyou.entity.Account;
import com.iloveyou.entity.Post;
import com.iloveyou.entity.Comment;
import com.iloveyou.repository.AnimalRepository;
import com.iloveyou.repository.PostRepository;
import com.iloveyou.repository.AccountRepository;
import com.iloveyou.repository.CommentRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final AccountRepository accountRepository; 
	private final AnimalRepository animalRepository;
	private final PostRepository postRepository;
	private final CommentRepository commentRepository;

	@Autowired
	public DatabaseLoader(AccountRepository accountRepository, AnimalRepository animalRepository, PostRepository postRepository, CommentRepository commentRepository) {
		this.accountRepository = accountRepository;
		this.animalRepository = animalRepository;
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
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

		// hard coded posts made for test
		Post p1 =
			Post.builder().title("This is test Post #1").author(u1).build();
		Post p2 =
			Post.builder().title("This is test Post #2").author(u2).build();
		Post p3 =
			Post.builder().title("This is test Announcement #1").author(u3).announcement(true).build();

		List<Post> posts = Arrays.asList(p1, p2, p3);
		this.postRepository.saveAll(posts);

		// hard coded comments made for test
		Comment c1 =
			Comment.builder().author(u2).post(p1).body("This is a comment for Post #1").build();
		Comment c2 =
			Comment.builder().author(u2).post(p2).body("This is a comment for Post #2").build();
		Comment c3 =
			Comment.builder().author(u2).post(p3).body("This is a comment for Post #3").build();

		List<Comment> comments = Arrays.asList(c1, c2, c3);
		this.commentRepository.saveAll(comments);

	}  
}