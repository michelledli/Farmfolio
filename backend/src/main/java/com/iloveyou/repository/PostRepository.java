package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iloveyou.entity.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> { 
    @Query("SELECT p FROM Post p WHERE p.announcement = true")
    List<Post> findAllAnnouncements();
}