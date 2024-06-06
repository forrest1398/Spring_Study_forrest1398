package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.Post;
import com.jungle.spring_study_forrest1398.dto.PostDto;
import com.jungle.spring_study_forrest1398.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = new PostDto(
                    post.getTitle(),
                    post.getContent(),
                    post.getWriter()
            );
            postDtos.add(postDto);
        }
        return postDtos;
    }

    ;

    public PostDto createPost(PostDto postDto) {
        Post a = new Post(
                postDto.getTitle(),
                postDto.getWriter(),
                postDto.getContent()
        );
        Post com = postRepository.save(a);
        return new PostDto(
                com.getTitle(),
                com.getContent(),
                com.getWriter()
        );
    }

    public PostDto getPostByID(Long postId) {
        Optional<Post> com = postRepository.findById(postId);
        return com.map(post -> new PostDto(
                post.getTitle(),
                post.getContent(),
                post.getWriter()
        )).orElse(null);
    }

    public PostDto updatePost(PostDto postDto) {

        Optional<Post> a = postRepository.findById(postDto.getId());
        if (a.isPresent()) {
            Post updatedPost = new Post(
                    a.get().getId(),
                    postDto.getTitle(),
                    a.get().getWriter(),
                    postDto.getContent()
            );
            Post com = postRepository.save(updatedPost);
            return new PostDto(
                    com.getTitle(),
                    com.getContent(),
                    com.getWriter()
            );
        }
        return null;

    }

    public void deltetPost(Long postId) {
        postRepository.deleteById(postId);
    }
}
