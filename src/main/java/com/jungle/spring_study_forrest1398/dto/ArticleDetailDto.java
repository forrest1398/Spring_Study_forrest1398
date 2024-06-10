package com.jungle.spring_study_forrest1398.dto;

import com.jungle.spring_study_forrest1398.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDetailDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdAt;

    public ArticleDetailDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}