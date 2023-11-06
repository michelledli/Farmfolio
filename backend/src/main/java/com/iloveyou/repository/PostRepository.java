package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iloveyou.entity.Comment;
import com.iloveyou.entity.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> { 

    // Fetch all announcements
    @Query(
        "SELECT p FROM Post p " +
        "WHERE p.announcement = true"
    )
    List<Post> findAllAnnouncements();

    //Fetch all comments that belong to a post
    // @Query(
    //     "SELECT c FROM Comment c " +
    //     "WHERE c.post = ?1"
    // )
    // List<Comment> getAllComments(Long id); 
}