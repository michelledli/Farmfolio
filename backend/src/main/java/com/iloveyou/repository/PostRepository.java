package com.iloveyou.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iloveyou.entity.Post;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public interface PostRepository extends JpaRepository<Post, Long> { 

    // Fetch all announcements
    @Query(
        "SELECT p FROM Post p " +
        "WHERE p.announcement = true"
    )
    List<Post> findAllAnnouncements();
}