package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.dto.PostDto;
import com.jungle.spring_study_forrest1398.service.PostService;
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
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    // 게시글 목록 조회
    @GetMapping
    public List<PostDto> getAllPostsDTO() {
        return postService.getAllPosts();
    }

    // 선택된 글의 정보 조회
    @GetMapping("/{postId}")
    public PostDto getPostDTO(@PathVariable Long postId) {
        return postService.getPostByID(postId);
    }

    // 게시글 수정
    @PutMapping
    public PostDto updatePost(@RequestBody PostDto postDto) {
        return postService.updatePost(postDto);
    }

    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable Long post_id) {
        postService.deltetPost(post_id);
    }

    //


}
