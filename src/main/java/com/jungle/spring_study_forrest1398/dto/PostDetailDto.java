package com.jungle.spring_study_forrest1398.dto;

import com.jungle.spring_study_forrest1398.domain.Post;
import lombok.Data;

@Data
public class PostDetailDto {
    private String title;
    private String writer;
    private String content;

    public PostDetailDto(Post post) {
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
    }
}