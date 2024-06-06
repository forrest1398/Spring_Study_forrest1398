package com.jungle.spring_study_forrest1398;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringStudyForrest1398Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyForrest1398Application.class, args);

        Long test1 = test();

    }


    public static Long test() {
        return 1L;
    }

}
