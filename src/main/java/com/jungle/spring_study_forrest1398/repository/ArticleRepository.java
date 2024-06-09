package com.jungle.spring_study_forrest1398.repository;

import com.jungle.spring_study_forrest1398.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByUpdatedAtDesc();
}

