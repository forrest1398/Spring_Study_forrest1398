package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.dto.UserDto;
import com.jungle.spring_study_forrest1398.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")// class level 맵핑
@RequiredArgsConstructor // DI
public class UserController {

    private final UserService userService;

    // 회원 가입
    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        
        return userService.createUser(userDto);
    }

}
