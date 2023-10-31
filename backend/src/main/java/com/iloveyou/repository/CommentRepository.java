package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iloveyou.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> { 

}