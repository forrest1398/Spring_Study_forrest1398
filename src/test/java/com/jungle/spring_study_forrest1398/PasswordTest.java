package com.jungle.spring_study_forrest1398;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("패스워드 비교")
    public void testPassword() {
        String testPassword = "123456";
        String encode1 = passwordEncoder.encode(testPassword);
        String encode2 = passwordEncoder.encode(testPassword);
        Assertions.assertThat(encode1).isEqualTo(encode2);
//        Assertions.assertThat(matches).isTrue();
    }
}
