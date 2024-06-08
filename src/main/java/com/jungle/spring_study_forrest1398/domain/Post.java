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
    long id;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String writer;
    @Column(nullable = true)
    String content;

    /* Todo : 회원 api 구현시 추가될 필드
    @ManyToOne // User 엔티티와의 ManyToOne 관계 설정
    @JoinColumn(name = "user_id")
    private User user;
    */

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.writer = postRequestDto.getWriter();
        this.content = postRequestDto.getContent();
    }

    // 필드 업데이트 메서드
    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.writer = postRequestDto.getWriter();
        this.content = postRequestDto.getContent();
    }

}
