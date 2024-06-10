package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.common.ResponseDto;
import com.jungle.spring_study_forrest1398.common.StatusEnum;
import com.jungle.spring_study_forrest1398.dto.LoginRequestDto;
import com.jungle.spring_study_forrest1398.dto.SignupRequestDto;
import com.jungle.spring_study_forrest1398.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        memberService.signup(signupRequestDto);
        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                "성공적으로 회원가입을 마쳤습니다."
        );
        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        memberService.login(loginRequestDto, response);
        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                "성공적으로 로그인을 마쳤습니다."
        );
        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

}
