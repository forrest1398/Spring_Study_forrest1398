package com.jungle.spring_study_forrest1398.controller;

import com.jungle.spring_study_forrest1398.common.ResponseDto;
import com.jungle.spring_study_forrest1398.common.StatusEnum;
import com.jungle.spring_study_forrest1398.domain.Article;
import com.jungle.spring_study_forrest1398.dto.ArticleDetailDto;
import com.jungle.spring_study_forrest1398.dto.ArticleRequestDto;
import com.jungle.spring_study_forrest1398.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")// class level 맵핑
@RequiredArgsConstructor // DI
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@RequestBody ArticleRequestDto articleRequestDto, HttpServletRequest request) {
        Article result = articleService.createPost(articleRequestDto, request);
        ArticleDetailDto articleDetailDto = new ArticleDetailDto(result);

        // 헤더 설정
        HttpHeaders header = new HttpHeaders();
        header.add("TestHeaderKey", "TestHeaderValue");

        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                articleDetailDto
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
        List<Article> articles = articleService.getPosts();
        List<ArticleDetailDto> articleDetailDtoList = articles.stream().map(ArticleDetailDto::new).toList();

        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                articleDetailDtoList
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    // 선택된 글의 정보 조회
    @GetMapping("/{articleId}")
    public ResponseEntity<ResponseDto> getPostByID(@PathVariable("articleId") Long articleId) {
        // dto 변환
        Article posts = articleService.getPostByID(articleId);
        ArticleDetailDto articleDetailDto = new ArticleDetailDto(posts);
        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                articleDetailDto
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    //todo: 서비스에서 jwt인증, 작성자이름 넣기
    // 게시글 수정
    @PutMapping("/{articleId}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDto articleRequestDto, HttpServletRequest request) {

        // dto 변환
        Article posts = articleService.updatePost(articleId, articleRequestDto, request);
        ArticleDetailDto articleDetailDto = new ArticleDetailDto(posts);
        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                articleDetailDto
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

    // 게시글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity<ResponseDto> deletePost(@PathVariable("articleId") Long articleId, HttpServletRequest request) {

        // dto 변환
        Long deletedId = articleService.deletePost(articleId, request);
        // ResponseDto의 status, message, data 설정
        ResponseDto dto = new ResponseDto(
                StatusEnum.OK,
                StatusEnum.OK.getMessage(),
                "성공적으로 게시글(id : " + deletedId.toString() + ")을 삭제했습니다."
        );

        // 응답 반환
        return ResponseEntity.status(HttpStatus.OK)
                .body(dto);
    }

}






