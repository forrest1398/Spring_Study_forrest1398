package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.Member;
import com.jungle.spring_study_forrest1398.domain.MemberRoleEnum;
import com.jungle.spring_study_forrest1398.dto.LoginRequestDto;
import com.jungle.spring_study_forrest1398.dto.SignupRequestDto;
import com.jungle.spring_study_forrest1398.jwt.JwtUtil;
import com.jungle.spring_study_forrest1398.repository.MemberRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    // ADMIN_TOKEN : 일단 여기에 위치
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    // Repository
    private final MemberRepository memberRepository;
    // JWT
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    // 비밀번호 암호화
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        // 비밀번호 암호화
        String password = bCryptPasswordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<Member> found = memberRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        // 사용자 ROLE 확인
        MemberRoleEnum role = MemberRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            // 관리자 토큰과 일치한다면 관리자 권한으로 설정
            role = MemberRoleEnum.ADMIN;
        }
        Member member = new Member(username, password, role);
        memberRepository.save(member);
    }

    @Transactional
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = bCryptPasswordEncoder.encode(loginRequestDto.getPassword());
        System.out.println("---------------");
        System.out.println(password);
        System.out.println("$2a$10$Pm2bHf1xpuNWWUcXlR2beOwfaYMeOabJyEAAyT0BDK6kkAC8QndnO");
        // 사용자 확인
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if (isMatchPassword(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getUsername(), member.getRole()));
    }

    private boolean isMatchPassword(String dbPassword, String reqPassword) {
        return passwordEncoder.matches(reqPassword, dbPassword);
    }
}
