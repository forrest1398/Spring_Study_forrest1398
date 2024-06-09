package com.jungle.spring_study_forrest1398.domain;

import com.jungle.spring_study_forrest1398.dto.ArticleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = true)
    private String content;

    // name : 외래키 명
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Member member;

    public Article(ArticleRequestDto articleRequestDto, Member member) {
        this.title = articleRequestDto.getTitle();
        this.writer = articleRequestDto.getWriter();
        this.content = articleRequestDto.getContent();
        this.member = member;
    }

    // 필드 업데이트 메서드
    public void update(ArticleRequestDto articleRequestDto) {
        this.title = articleRequestDto.getTitle();
        this.writer = articleRequestDto.getWriter();
        this.content = articleRequestDto.getContent();
    }

}
