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

		Account admin = Account.builder().fname("admin").lname("").email("admin").password("a").isAdmin(true).build();
		Account user = Account.builder().fname("user").lname("").email("user").password("a").isAdmin(false).build();

		Account u1 = Account.builder().fname("First 1").lname("Last 1").email("user-1").password("a").isAdmin(false).build();
		Account u2 = Account.builder().fname("First 2").lname("Last 2").email("user-2").password("a").isAdmin(false).build();
		Account u3 = Account.builder().fname("First 3").lname("Last 3").email("user-3").password("a").isAdmin(false).build();
		Account u4 = Account.builder().fname("First 4").lname("Last 4").email("user-4").password("a").isAdmin(false).build();

		List<Account> accounts = Arrays.asList(admin, user, u1, u2, u3, u4);
		this.accountRepository.saveAll(accounts);

		Animal a1 = Animal.builder().name("Animal 1").dob(new Date()).weight(100).tag("Tag 1").breed("Breed 1").build();
		Animal a2 = Animal.builder().name("Animal 2").dob(new Date()).weight(120).tag("Tag 2").breed("Breed 2").build();
		Animal a3 = Animal.builder().name("Animal 3").dob(new Date()).weight(130).tag("Tag 3").breed("Breed 3").build();
		Animal a4 = Animal.builder().name("Animal 4").dob(new Date()).weight(140).tag("Tag 4").breed("Breed 4").build();
		Animal a5 = Animal.builder().name("Animal 5").dob(new Date()).weight(150).tag("Tag 5").breed("Breed 5").build();
		Animal a6 = Animal.builder().name("father").dob(new Date()).weight(160).tag("y").breed("c").build();
		Animal a7 = Animal.builder().name("mother").dob(new Date()).weight(170).tag("y").breed("c").build();
		Animal a8 = Animal.builder().name("child").dob(new Date()).weight(180).tag("y").breed("c").build();
		
		a8.setFather(a6);
		a8.setMother(a7);

		List<Animal> animals = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8);
		this.animalRepository.saveAll(animals);

		// hard coded posts made for test
		Post p1 = Post.builder().title("Title 1").body("Body 1").author(u1).createdAt("01/01/1970").build();
		Post p2 = Post.builder().title("Title 2").body("Body 2").author(u2).createdAt("02/02/1970").build();
		Post p3 = Post.builder().title("Title 3").body("Body 3").author(u3).createdAt("03/03/1970").build();
		Post p4 = Post.builder().title("Title 4").body("Body 4").author(u4).createdAt("04/04/1970").build();

		Post pa1 = Post.builder().title("Announcement 1").body("Announcement Body 1").author(u3).createdAt("01/01/1970").announcement(true).build();
		Post pa2 = Post.builder().title("Announcement 2").body("Announcement Body 2").author(u3).createdAt("02/02/1970").announcement(true).build();
		Post pa3 = Post.builder().title("Announcement 3").body("Announcement Body 3").author(u3).createdAt("03/03/1970").announcement(true).build();

		List<Post> posts = Arrays.asList(p1, p2, p3, p4, pa1, pa2, pa3);
		this.postRepository.saveAll(posts);

		// hard coded comments made for test
		Comment p1c1 = Comment.builder().author(u1).postId(1L).body("Post 1 - Comment 1").createdAt("01/01/1970").build();
		Comment p1c2 = Comment.builder().author(u2).postId(1L).body("Post 1 - Comment 2").createdAt("02/02/1970").build();
		Comment p1c3 = Comment.builder().author(u3).postId(1L).body("Post 1 - Comment 3").createdAt("03/03/1970").build();

		Comment p2c1 = Comment.builder().author(u1).postId(2L).body("Post 2 - Comment 1").createdAt("01/01/1970").build();
		Comment p2c2 = Comment.builder().author(u2).postId(2L).body("Post 2 - Comment 2").createdAt("02/02/1970").build();
		Comment p2c3 = Comment.builder().author(u3).postId(2L).body("Post 2 - Comment 3").createdAt("03/03/1970").build();

		Comment p3c1 = Comment.builder().author(u1).postId(3L).body("Post 3 - Comment 1").createdAt("01/01/1970").build();
		Comment p3c2 = Comment.builder().author(u2).postId(3L).body("Post 3 - Comment 2").createdAt("02/02/1970").build();
		Comment p3c3 = Comment.builder().author(u3).postId(3L).body("Post 3 - Comment 3").createdAt("03/03/1970").build();


		List<Comment> comments = Arrays.asList(p1c1, p1c2, p1c3,p2c1, p2c2, p2c3,p3c1, p3c2, p3c3);
		this.commentRepository.saveAll(comments);
	}
}