package com.iloveyou.controller;

import com.iloveyou.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iloveyou.entity.Account;

import java.util.List;

import com.iloveyou.entity.Post;
import com.iloveyou.repository.PostRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/posts")
@RestController
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    AccountRepository accountRepository;

    // GET /api/posts
    @GetMapping()
    List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // GET /api/posts/:id
    @GetMapping("/{id}")
    Optional<Post> getPostById(@PathVariable long id) {
        return postRepository.findById(id);
    }

    /* POST /api/posts
        {
            body:
            title:
        }
    }
    @PostMapping("/{accountId}")
    public Post createPost(@RequestBody Post post, @PathVariable Long accountId) {
 
        Account account = accountRepository.findById(accountId).get();
        post.setAuthor(account);
 
        // Get the current date and time
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String formattedDate = simpleDateFormat.format(currentDate);
        post.setCreatedAt(formattedDate );
        
        return postRepository.save(post);
    }
    */

    /* POST /api/posts
    {
        body:
        title:
    }*/
    @PostMapping()
    public ResponseEntity<?> createPost(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        Long accountId = Long.valueOf(claims.getId());
        Account account = accountRepository.findById(accountId).get();

        try {
            var body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            ObjectMapper mapper = new ObjectMapper();
            Post post = mapper.readValue(body, Post.class);

            post.setAuthor(account);

            // Get the current date and time
            Date currentDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String formattedDate = simpleDateFormat.format(currentDate);
            post.setCreatedAt(formattedDate );

            return ResponseEntity.ok(postRepository.save(post));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    // DELETE /api/posts/:id
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
    }

    // PUT /api/posts/:id
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePost(@RequestBody Post post, @PathVariable Long id) {
        // find the target post using the path variable id
        Optional<Post> targetPost = postRepository.findById(id);

        // check if the post is empty or not
        if (!targetPost.isPresent())
            // if empty, respond with not found
            return ResponseEntity.notFound().build();
        
        // the target post exists, update the comment
        // first create a Post type object
        Post temp = targetPost.get();
        temp.setTitle(post.getTitle());
        temp.setBody(post.getBody());
        
        postRepository.save(post);
        return ResponseEntity.ok().build();
    }

    // GET /api/posts/announcements
    @GetMapping("/announcements")
    List<Post> getAllAnouncments() {
        return postRepository.findAllAnnouncements();
    }

    // POST /api/posts/announcements
    @PostMapping("/announcements")
    public Post createAnnouncement(@RequestBody Post post) {
        post.setAnnouncement(true);
        return postRepository.save(post);
    }
    
    /* 
    // PUT /api/posts/announcements/id
    @PatchMapping("/announcements/{id}")
    public ResponseEntity<Object> updateAnnouncement(@RequestBody Post post, @PathVariable("id") Long id) {
        // find the target post using the path variable id
        Optional<Post> targetPost = postRepository.findById(id);

        // check if the post is empty or not
        if (!targetPost.isPresent())
            // if empty, respond with not found
            return ResponseEntity.notFound().build();
        
        // the target post exists, update the comment
        // first create a Post type object
        Post temp = targetPost.get();
        temp.setTitle(post.getTitle());
        
        postRepository.save(post);
        return ResponseEntity.noContent().build();
    }
    */    
}