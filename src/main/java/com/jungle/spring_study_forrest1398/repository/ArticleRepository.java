package com.jungle.spring_study_forrest1398.repository;

import com.jungle.spring_study_forrest1398.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 생성 날짜 기준 내림차순으로 받아오기
    List<Article> findAllByOrderByCreatedAtDesc();
}

