package com.iloveyou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iloveyou.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { 
    // Fetch comments by post id
    List<Comment> findByPostId(Long id);
}
