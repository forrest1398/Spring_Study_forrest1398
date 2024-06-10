package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.Article;
import com.jungle.spring_study_forrest1398.domain.Member;
import com.jungle.spring_study_forrest1398.dto.ArticleRequestDto;
import com.jungle.spring_study_forrest1398.jwt.JwtUtil;
import com.jungle.spring_study_forrest1398.repository.ArticleRepository;
import com.jungle.spring_study_forrest1398.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public Article createPost(ArticleRequestDto articleRequestDto, HttpServletRequest request) {
        // Todo : null일 경우 예외처리 필요
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
//
//        //토큰이 있는 경우
//        if (token != null) {
//            // 토큰이 유효한지 검증
//            if (jwtUtil.validateToken(token)) {
//                // 유효하다면, 토큰에서 사용자 데이터 가져오기
//                claims =
//                // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
//                Member member = memberRepository.findByUsername(claims.getSubject()).orElseThrow(
//                        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
//                );
//                articleRequestDto.setWriter(claims.getSubject());
//                // 게시글 생성
//                Article article = new Article(articleRequestDto, member);
//                return articleRepository.save(article);
//            }
//            // 유효하지 않다면 예외처리
//            else {
//                throw new IllegalArgumentException("Token invalid");
//            }
//        }
//        // 토큰이 없다면
//        else {
//            return null;
//        }
        System.out.println(claims.get("username", String.class));
        Member member = memberRepository.findByUsername(claims.get("username", String.class)).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        // 게시글 생성
        articleRequestDto.setWriter(claims.get("username", String.class));
        Article article = new Article(articleRequestDto, member);
        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public List<Article> getPosts() {
        return articleRepository.findAllByOrderByUpdatedAtDesc();
    }

    @Transactional
    public Article getPostByID(Long postId) {
        return articleRepository.findById(postId).orElseThrow(null);
    }

    @Transactional
    public Long updatePost(Long postId, ArticleRequestDto articleRequestDto) {
        Article article = articleRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        article.update(articleRequestDto);
        return article.getId();
    }

    @Transactional
    public Long deletePost(Long postId) {
        articleRepository.deleteById(postId);
        return postId;
    }
}