package com.jungle.spring_study_forrest1398.security;

import com.jungle.spring_study_forrest1398.jwt.JwtFilter;
import com.jungle.spring_study_forrest1398.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration// 이 클래스가 spring boot에게 configuration 클래스로서 관리되기 위한 설정 어노테이션
@EnableWebSecurity// 이 configuration 클래스가 security를 위한 config이기 때문에 붙이는 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    
    // LoginFilter에서 사용하는 AuthenticationManager를 Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 회원정보를 저장하고, 회원가입하고, 검증할 때는 항상, 비밀번호를 hash로 암호화해서 진행되기 때문에
    // 암호화를 진행하는 함수 bean으로 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // csrf disable
                // 세션에서 필요하지만 jwt에서는 필요 없다.
                .csrf(AbstractHttpConfigurer::disable)
                //From 로그인 방식 disable
                .formLogin(AbstractHttpConfigurer::disable)
                //http basic 인증 방식 disable
                .httpBasic(AbstractHttpConfigurer::disable)
                // jwt에서는 세션을 항상 stateless상태로 관리하는 설정, 가장 중요하다!!
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // filter chain에 LoginFilter 추가
        // 1. LoginFilter는 UsernamePasswordAuthenticationFilter의 대체이기 때문에 그 위치에 넣어야 한다. -> addFilterAt
        // 2. LoginFilter()는 인자를 받음
        // 2-1. AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함)
        // 2-2. 따라서 등록 필요 -> 위에서 한 작업
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);
        //JWTFilter 등록
        http
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //경로별 인가 작업
        http.authorizeHttpRequests(authorize -> authorize
                // 누구에게나 열린 경로
                .requestMatchers("/member/login", "/member/signup", "/article").permitAll()
                // "/article/articleId" 경로는 get요청만 열린 경로로 설정
                .requestMatchers(HttpMethod.GET, "/article/**").permitAll()
                // "ADMIN" 권한을 가진, 관리자만 접근 가능한 경로
                .requestMatchers("/admin").hasRole("ADMIN")
                // 그 외의 경로
                // 로그인 된, 일반 유저만 접근 가능한 경로
                .anyRequest().authenticated());

        return http.build();
    }
}