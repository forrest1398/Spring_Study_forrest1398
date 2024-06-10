package com.jungle.spring_study_forrest1398.service;


import com.jungle.spring_study_forrest1398.domain.Member;
import com.jungle.spring_study_forrest1398.dto.CustomUserDetails;
import com.jungle.spring_study_forrest1398.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        //DB에서 조회
        Optional<Member> memberData = memberRepository.findByUsername(username);
        if (memberData.isPresent()) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(memberData.get());
        }

        return null;
    }
}