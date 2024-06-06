package com.jungle.spring_study_forrest1398.repository;

import com.jungle.spring_study_forrest1398.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAll();

    Optional<Post> findOneById(Long post_id);
}

