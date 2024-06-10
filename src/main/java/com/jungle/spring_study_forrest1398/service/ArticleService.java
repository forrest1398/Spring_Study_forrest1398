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
        return articleRepository.findAllByOrderByCreatedAtDesc();
    }

    @Transactional
    public Article getPostByID(Long postId) {
        return articleRepository.findById(postId).orElseThrow(null);
    }

    @Transactional
    public Article updatePost(Long postId, ArticleRequestDto articleRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        Article article = articleRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
                IllegalArgumentException::new
        );
        if (article.getWriter() != claims.get("username", String.class))
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다.");
        article.update(articleRequestDto);
        return article;
    }

    @Transactional
    public Long deletePost(Long postId, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = jwtUtil.getUserInfoFromToken(token);
        Article article = articleRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if (article.getWriter() != claims.get("username", String.class))
            throw new IllegalArgumentException("게시글의 작성자가 아닙니다.");
        articleRepository.deleteById(postId);
        return postId;
    }
}