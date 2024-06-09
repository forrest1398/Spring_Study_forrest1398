package com.jungle.spring_study_forrest1398.dto;

import com.jungle.spring_study_forrest1398.domain.Article;
import lombok.Data;

@Data
public class ArticleDetailDto {
    private String title;
    private String writer;
    private String content;

    public ArticleDetailDto(Article article) {
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.content = article.getContent();
    }
}