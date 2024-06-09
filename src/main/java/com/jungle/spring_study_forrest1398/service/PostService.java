package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.Post;
import com.jungle.spring_study_forrest1398.domain.User;
import com.jungle.spring_study_forrest1398.dto.PostRequestDto;
import com.jungle.spring_study_forrest1398.jwt.JwtUtil;
import com.jungle.spring_study_forrest1398.repository.PostRepository;
import com.jungle.spring_study_forrest1398.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public Post createPost(PostRequestDto postRequestDto, HttpServletRequest request) {
        // Todo : null일 경우 예외처리 필요
        String token = jwtUtil.resolveToken(request);

        Claims claims;

        //토큰이 있는 경우
        if (token != null) {
            // 토큰이 유효한지 검증
            if (jwtUtil.validateToken(token)) {
                // 유효하다면, 토큰에서 사용자 데이터 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

                // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
                User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                        () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
                );
                postRequestDto.setWriter(claims.getSubject());
                // 게시글 생성
                Post post = new Post(postRequestDto, user);
                return postRepository.save(post);
            }
            // 유효하지 않다면 예외처리
            else {
                throw new IllegalArgumentException("Token invalid");
            }
        }
        // 토큰이 없다면
        else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }

    @Transactional
    public Post getPostByID(Long postId) {
        return postRepository.findById(postId).orElseThrow(null);
    }

    @Transactional
    public Long updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        post.update(postRequestDto);
        return post.getId();
    }

    @Transactional
    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }
}