package com.iloveyou.controller;

import com.iloveyou.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.iloveyou.entity.Account;


import java.util.List;

import com.iloveyou.entity.Post;
import com.iloveyou.repository.PostRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        body: {
            accountId:
            title:
        }
    }*/
    @PostMapping("/create/{accountId}")
    public Post createPost(@RequestBody Post post, @PathVariable Long accountId) {
        Account account = accountRepository.findById(accountId).get();
        post.setAuthor(account);
        post.setCreatedAt(new Date());
        return postRepository.save(post);
    }

    // DELETE /api/posts/:id
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
    }

    // PUT /api/posts/:id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePost(@RequestBody Post post, @PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(post.getId());
        if (!postOptional.isPresent())
            return ResponseEntity.notFound().build();
        post.setId(id);
        postRepository.save(post);
        return ResponseEntity.noContent().build();
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
    
    // PUT /api/posts/announcements/id
    @PatchMapping("/announcements/{id}")
    public ResponseEntity<Object> updateAnnouncement(@RequestBody Post post, @PathVariable Long id) {
        Optional<Post> postOptional = postRepository.findById(post.getId());
        if (!postOptional.isPresent())
            return ResponseEntity.notFound().build();
        
        post.setId(id);
        postRepository.save(post);
        return ResponseEntity.noContent().build();
    }    
}