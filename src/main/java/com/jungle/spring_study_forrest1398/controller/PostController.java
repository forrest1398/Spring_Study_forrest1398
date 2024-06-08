package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.domain.Post;
import com.jungle.spring_study_forrest1398.dto.PostDetailDto;
import com.jungle.spring_study_forrest1398.dto.PostRequestDto;
import com.jungle.spring_study_forrest1398.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")// class level 맵핑
@RequiredArgsConstructor // DI
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping
    public Post createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.createPost(postRequestDto, request);
    }

    // 게시글 목록 조회
    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    // 선택된 글의 정보 조회
    @GetMapping("/{postId}")
    public PostDetailDto getPostByID(@PathVariable Long postId) {
        return new PostDetailDto(postService.getPostByID(postId));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, postRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }

}
