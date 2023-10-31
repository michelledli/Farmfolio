package com.iloveyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.iloveyou.entity.Account;
import com.iloveyou.entity.Animal;
import com.iloveyou.repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List;

import com.iloveyou.entity.Comment;
import com.iloveyou.repository.CommentRepository;
import java.util.Date;
import java.util.Optional;

@RequestMapping("/comments")
@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    // GET /api/comments
    @GetMapping()
    List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // GET /api/comments/:id
    @GetMapping("/{id}")
    Optional<Comment> getCommentById(@PathVariable long id) {
        return commentRepository.findById(id);
    }

    /* POST /api/comments
        body: {
            postId:
            accountId:
            body:
        }
    }*/
    @PostMapping()
    public Comment createComment(@RequestBody Comment comment) {
        comment.setCreatedAt(new Date());
        return commentRepository.save(comment);
    }

    // DELETE /api/comments/delete/:id
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") Long id) {
        commentRepository.deleteById(id);
    }

    // PUT /api/comments/update/:id
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateComment(@RequestBody Comment comment, @PathVariable("id") Long id) {
        // find the target comment using the path variable id
        Optional<Comment> targetComment = commentRepository.findById(id);

        // check if the comment is empty or not
        if (!targetComment.isPresent())
            // if empty, respond with not found
            return ResponseEntity.notFound().build();
        
        // the target comment exists, update the comment
        // first create a Comment type object
        Comment temp = targetComment.get();
        temp.setBody(comment.getBody());

        commentRepository.save(temp);
        return ResponseEntity.noContent().build();
    }
}
