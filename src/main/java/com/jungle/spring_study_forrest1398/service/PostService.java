package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.Post;
import com.jungle.spring_study_forrest1398.dto.PostRequestDto;
import com.jungle.spring_study_forrest1398.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDto postRequestDto) {
        // Todo : null일 경우 예외처리 필요
        Post post = new Post(postRequestDto);
        return postRepository.save(post);
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
