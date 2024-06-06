package com.jungle.spring_study_forrest1398.service;

import com.jungle.spring_study_forrest1398.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    private  final PostRepository postRepository;
}
