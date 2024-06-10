package com.jungle.spring_study_forrest1398.jwt;

import com.jungle.spring_study_forrest1398.domain.Member;
import com.jungle.spring_study_forrest1398.domain.MemberRoleEnum;
import com.jungle.spring_study_forrest1398.dto.CustomUserDetails;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String authrization = request.getHeader("Authorization");
        //Authorization 헤더 검증
        if (authrization == null || !authrization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 토큰의 유효성 검사
        // 이거 넘겨서 안으로 들어와야 한다.
        if (!jwtUtil.validateToken(authrization.split(" ")[1])) {
            filterChain.doFilter(request, response);
            return;
        }
        //토큰에서 username과 role 획득
        Claims userInfo = jwtUtil.getUserInfoFromToken(authrization.split(" ")[1]);
        String username = userInfo.get("username", String.class);
        String role = userInfo.get("auth", String.class);
        MemberRoleEnum roleEnum = MemberRoleEnum.valueOf(role);
        //userEntity를 생성하여 값 set
        Member userEntity = new Member(username, "tempPassword", roleEnum);

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
