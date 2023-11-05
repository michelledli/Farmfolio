package com.iloveyou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iloveyou.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { 
    // // Fetch comments by post id
    // @Query(
    //     "SELECT c FROM Comment c " +
    //     "WHERE c.postId = ?1"
    // )
    // List<Comment> findByPostId(long id);
}
