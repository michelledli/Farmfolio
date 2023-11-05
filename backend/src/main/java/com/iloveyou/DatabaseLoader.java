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
	public DatabaseLoader(AccountRepository accountRepository, AnimalRepository animalRepository,
			PostRepository postRepository, CommentRepository commentRepository) {
		this.accountRepository = accountRepository;
		this.animalRepository = animalRepository;
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public void run(String... strings) throws Exception {
		// hard coded users made for testing
		Account u1 = Account.builder().fname("Matt").lname("Tai").email("mt9123@gmail.com").password("password")
				.build();
		Account u2 = Account.builder().fname("Pat").lname("Lai").email("patlai@csus.edu").password("password").build();
		Account u3 = Account.builder().fname("Kat").lname("Shai").email("kshai916@hotmail.com").password("password")
				.build();

		Account userEntity = Account.builder().fname("fname").lname("lname").email("user").password("a").isAdmin(false)
				.build();

		Account adminEntity = Account.builder().fname("fname").lname("lname").email("admin").password("a").isAdmin(true)
				.build();

		List<Account> accounts = Arrays.asList(u1, u2, u3, adminEntity, userEntity);
		this.accountRepository.saveAll(accounts);

		// hard coded animals made for testing
		Animal a1 = Animal.builder().name("Penny").dob(new Date()).weight(80).tag("x").breed("a").build();
		Animal a2 = Animal.builder().name("Kenny").dob(new Date()).weight(100).tag("z").breed("b").build();
		Animal a3 = Animal.builder().name("Benny").dob(new Date()).weight(75).tag("y").breed("b").build();
		Animal a4 = Animal.builder().name("Jenny").dob(new Date()).weight(120).tag("x").breed("a").build();
		Animal a5 = Animal.builder().name("Denny").dob(new Date()).weight(135).tag("y").breed("c").build();

		Animal a6 = Animal.builder().name("father").dob(new Date()).weight(150).tag("y").breed(
				"c").build();

		Animal a7 = Animal.builder().name("mother").dob(new Date()).weight(100).tag("y").breed(
				"c").build();

		Animal a8 = Animal.builder().name("child").dob(new Date()).weight(70).tag("y").breed(
				"c").build();
		a8.setFather(a6);
		a8.setMother(a7);

		List<Animal> animals = Arrays.asList(a1, a2, a3, a4, a5, a6,
				a7, a8);
		this.animalRepository.saveAll(animals);

		// hard coded posts made for test
		Post p1 = Post.builder().title("This is Title #1").body("This is Body #1").author(u1).createdAt("6/3/2023")
				.build();
		Post p2 = Post.builder().title("This is Title #2").body("This is Body #2").author(u2).createdAt("12/4/2023")
				.build();
		Post p3 = Post.builder().title("This is test Announcement Title #1").body("This is Announcement Body #1")
				.author(u3).createdAt("11/5/2023").announcement(true).build();

		List<Post> posts = Arrays.asList(p1, p2, p3);
		this.postRepository.saveAll(posts);

		// hard coded comments made for test
		Comment c1 = Comment.builder().postId((long) 1).author(u1).body("This is a comment for Post #1")
						.createdAt("10/8/2023").build();
		Comment c2 = Comment.builder().postId((long) 2).author(u2).body("This is a comment for Post #2")
				.createdAt("10/9/2023").build();
		Comment c3 = Comment.builder().postId((long) 3).author(u3).body("This is a comment for Post #3")
				.createdAt("10/11/2023").build();

		List<Comment> comments = Arrays.asList(c1, c2, c3);
		this.commentRepository.saveAll(comments);

	}
}