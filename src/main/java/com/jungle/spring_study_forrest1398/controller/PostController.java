package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.common.ResponseDto;
import com.jungle.spring_study_forrest1398.common.StatusEnum;
import com.jungle.spring_study_forrest1398.domain.Post;
import com.jungle.spring_study_forrest1398.dto.PostDetailDto;
import com.jungle.spring_study_forrest1398.dto.PostRequestDto;
import com.jungle.spring_study_forrest1398.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")// class level 맵핑
@RequiredArgsConstructor // DI
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        Post result = postService.createPost(postRequestDto, request);
        PostDetailDto postDetailDto = new PostDetailDto(result);

        // 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("TestHeaderKey", "TestHeaderValue");

        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                postDetailDto
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .headers(header)
                .body(dto);
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<ResponseDto> getPosts() {
        // dto 변환
        List<Post> posts = postService.getPosts();
        List<PostDetailDto> postDetailDtoList = posts.stream().map(PostDetailDto::new).toList();

        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                postDetailDtoList
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    // 선택된 글의 정보 조회
    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto> getPostByID(@PathVariable Long postId) {
        // dto 변환
        Post posts = postService.getPostByID(postId);
        PostDetailDto postDetailDto = new PostDetailDto(posts);

        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                postDetailDto
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    //todo: 서비스에서 jwt인증, 작성자이름 넣기
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






