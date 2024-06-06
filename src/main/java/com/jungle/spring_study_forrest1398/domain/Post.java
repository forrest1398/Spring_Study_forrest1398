package com.jungle.spring_study_forrest1398.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

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
    public Post(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

}
