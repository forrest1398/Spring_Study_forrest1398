package com.jungle.spring_study_forrest1398.domain;

import com.jungle.spring_study_forrest1398.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {

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
    private User user;

    public Post(PostRequestDto postRequestDto, User user) {
        this.title = postRequestDto.getTitle();
        this.writer = postRequestDto.getWriter();
        this.content = postRequestDto.getContent();
        this.user = user;
    }

    // 필드 업데이트 메서드
    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.writer = postRequestDto.getWriter();
        this.content = postRequestDto.getContent();
    }

}
