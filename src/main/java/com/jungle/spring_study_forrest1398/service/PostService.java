package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.domain.Post;
import com.jungle.spring_study_forrest1398.dto.PostDto;
import com.jungle.spring_study_forrest1398.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                postDto.getContent(),
                postDto.getWriter()
        );
        Post com = postRepository.save(a);
        return new PostDto(
                com.getTitle(),
                com.getContent(),
                com.getWriter()
        );
    }
}
