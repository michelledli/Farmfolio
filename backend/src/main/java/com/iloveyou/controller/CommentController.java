package com.iloveyou.controller;

import com.iloveyou.entity.Account;
import com.iloveyou.repository.AccountRepository;
import com.iloveyou.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.iloveyou.entity.Comment;
import com.iloveyou.repository.CommentRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/comments")
@RestController
public class CommentController {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AccountRepository accountRepository;

    // GET /api/comments
    @GetMapping()
    List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // GET /api/comments/posts/:id
    // @GetMapping("/posts")
    // List<Comment> getCommentByPostId(@RequestParam long id) {
    //     return commentRepository.findByPostId(id);
    // }

    // GET /api/comments/:id
    @GetMapping("/{id}")
    Optional<Comment> getCommentById(@PathVariable long id) {
        return commentRepository.findById(id);
    }

    /*
     * POST /api/comments/userId/postId
     * body: {
     * body:
     * }
     * }
     */

    @PostMapping()
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        Account account = accountRepository.findById(comment.getAuditId()).get();
        comment.setAuthor(account);

        // Get the current date and time
        Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String formattedDate = simpleDateFormat.format(currentDate);
        comment.setCreatedAt(formattedDate);

        return ResponseEntity.ok(commentRepository.save(comment));

    }

    // DELETE /api/comments/delete/:id
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteComment(@PathVariable("id") Long id) {
        commentRepository.deleteById(id);
    }

    // PUT /api/comments/update/:id
    @PutMapping("/{id}")
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
        return ResponseEntity.ok().build();
    }
}
