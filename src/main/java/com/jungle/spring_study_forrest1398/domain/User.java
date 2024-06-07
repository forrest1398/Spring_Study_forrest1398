package com.jungle.spring_study_forrest1398.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(nullable = false)
    public String userName;

    @Column(nullable = false)
    public String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
