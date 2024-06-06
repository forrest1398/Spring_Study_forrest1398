package com.jungle.spring_study_forrest1398.domain;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id ;

    @Column(nullable = false)
    String title ;
    @Column(nullable = false)
    String writer ;
    @Column(nullable = true)
    String content ;
    @Column(nullable = false)
    String date_created;

    /* Todo : 회원 api 구현시 추가
    @ManyToOne // User 엔티티와의 ManyToOne 관계 설정
    @JoinColumn(name = "user_id")
    private User user;
    */

}
