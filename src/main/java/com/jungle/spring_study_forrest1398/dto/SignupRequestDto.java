package com.jungle.spring_study_forrest1398.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
    // 관리자 권환 관련 필드
    private boolean admin = false;
    private String adminToken = "";
}
