package com.jungle.spring_study_forrest1398.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    @Size(min = 4, max = 10, message = "아이디는 4글자 이상, 10글자 이하여야 합니다.")
    @Pattern(regexp = "[a-z0-9]+$", message = "아이디는 영어 소문자와 숫자로 구성되어야 합니다.")
    private String userName;

    @Size(min = 8, max = 15, message = "비밀번호는 4글자 이상, 10글자 이하여야 합니다.")
    @Pattern(regexp = "[A-Za-z0-9]+$", message = "비밀번호는 영어 소문자와 숫자로 구성되어야 합니다.")
    private String password;
}
